package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.woods.tcc.dto.ProviderDTO;
import com.woods.tcc.model.Address;
import com.woods.tcc.model.Provider;
import com.woods.tcc.services.ProviderService;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/provider")
public class ProviderController {
  @Autowired
  private ProviderService providerService;
  @Autowired
  private PasswordEncoder encoder;

  @GetMapping
  public ResponseEntity<List<ProviderDTO>> findAll() {
    List<Provider> lProviders = this.providerService.findAll();

    if(lProviders.isEmpty())
      throw new EntityNotFoundException("There is no providers found");

    List<ProviderDTO> lProviderDTOs = lProviders
    .stream()
    .map(x -> new ProviderDTO(x))
    .collect(Collectors.toList());

    return ResponseEntity.ok().body(lProviderDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProviderDTO> findById(@PathVariable Long id) {
    try {
      Provider provider = this.providerService.findById(id);
      ProviderDTO providerDTO = new ProviderDTO(provider);
      return ResponseEntity.ok().body(providerDTO);
    } catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @GetMapping(value = "/login")
  public ResponseEntity<String> passwordValidate(
    @RequestParam String email,
     @RequestParam String password
  ) {

    Optional<Provider> oProvider = this.providerService.findByEmail(email);
    String msg = "";
    if(oProvider.isEmpty()){
      msg = "Error: Email not found";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    Boolean isValid = this.encoder.matches(password, oProvider.get().getPassword());
    HttpStatus status = isValid ? HttpStatus.ACCEPTED : HttpStatus.UNAUTHORIZED;
    msg = status.is2xxSuccessful() ? "Successful" : "Error: Invalid Email or Password!";
    return ResponseEntity.status(status).body(msg);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<ProviderDTO> create(@RequestBody ProviderDTO providerDTO) {
    Address address = Address
    .builder()
    .id(providerDTO.getAddressId())
    .build();

    Provider provider = Provider.builder()
    .cnpj(providerDTO.getCnpj())
    .build();
    provider.setAddress(address);
    provider.setEmail(providerDTO.getEmail());
    provider.setImageUrl(providerDTO.getImageUrl());
    provider.setName(providerDTO.getEmail());
    provider.setPhone(providerDTO.getPhone());
    provider.setUserName(providerDTO.getUserName());
    provider.setPassword(this.encoder.encode(providerDTO.getPassword()));

    try {
      provider = this.providerService.create(provider);
    } catch (DataIntegrityViolationException e) {
      throw new EntityNotFoundException("Error: a valid address id is required or this email is already registered");
    }

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(provider.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new ProviderDTO(provider));
  }

  @DeleteMapping(value ="/delete/{id}")
  public ResponseEntity<String> deleteById (@PathVariable Long id, @RequestBody ProviderDTO providerDTO){
    this.providerService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successful provider deletion");
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ProviderDTO> updateById (@PathVariable Long id, @RequestBody ProviderDTO providerDTO) {
    Address address = Address
    .builder()
    .id(providerDTO.getAddressId())
    .build();

    Provider provider = Provider.builder()
    .cnpj(providerDTO.getCnpj())
    .build();
    provider.setAddress(address);
    provider.setEmail(providerDTO.getEmail());
    provider.setImageUrl(providerDTO.getImageUrl());
    provider.setName(providerDTO.getEmail());
    provider.setPhone(providerDTO.getPhone());
    provider.setUserName(providerDTO.getUserName());
    provider.setPassword(this.encoder.encode(providerDTO.getPassword()));

    provider = this.providerService.update(id, provider);

    if(provider == null){
      throw new EntityNotFoundException(id);
    }
    return  ResponseEntity.ok().body(providerDTO);
  }
}
