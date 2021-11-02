package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.woods.tcc.dto.ClientDTO;
import com.woods.tcc.model.Address;
import com.woods.tcc.model.Client;
import com.woods.tcc.services.ClientService;
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
@RequestMapping(value = "/clients")
public class ClientController {
  @Autowired
  private ClientService clientService;
  @Autowired
  private PasswordEncoder encoder;

  @GetMapping
  public ResponseEntity<List<ClientDTO>> findAll() {
    List<Client> lClients = this.clientService.findAll();

    if(lClients.isEmpty())
      throw new EntityNotFoundException("There is no clients found");

    List<ClientDTO> lClientDTOs = lClients
    .stream()
    .map(x -> new ClientDTO(x))
    .collect(Collectors.toList());

    return ResponseEntity.ok().body(lClientDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
    try {
      Client client = this.clientService.findById(id);
      ClientDTO clientDTO = new ClientDTO(client);
      return ResponseEntity.ok().body(clientDTO);
    } catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @GetMapping(value = "/login")
  public ResponseEntity<String> passwordValidate(
    @RequestParam String email,
     @RequestParam String password
  ) {

    Optional<Client> oClient = this.clientService.findByEmail(email);
    String msg = "";
    if(oClient.isEmpty()){
      msg = "Error: Email not found";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    Boolean isValid = this.encoder.matches(password, oClient.get().getPassword());
    HttpStatus status = isValid ? HttpStatus.ACCEPTED : HttpStatus.UNAUTHORIZED;
    msg = status.is2xxSuccessful() ? "Successful" : "Error: Invalid Email or Password!";
    return ResponseEntity.status(status).body(msg);
  }

  @PostMapping(value = "/create")
  public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
    Address address = Address
    .builder()
    .id(clientDTO.getAddressId())
    .build();

    Client client = Client.builder()
    .cpf(clientDTO.getCpf())
    .build();
    client.setAddress(address);
    client.setEmail(clientDTO.getEmail());
    client.setImageUrl(clientDTO.getImageUrl());
    client.setName(clientDTO.getEmail());
    client.setPhone(clientDTO.getPhone());
    client.setUserName(clientDTO.getUserName());
    client.setPassword(this.encoder.encode(clientDTO.getPassword()));

    try {
      client = this.clientService.create(client);
    } catch (DataIntegrityViolationException e) {
      throw new EntityNotFoundException("Error: a valid address id is required or this email is already registered");
    }

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(client.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new ClientDTO(client));
  }

  @DeleteMapping(value ="/delete/{id}")
  public ResponseEntity<String> deleteById (@PathVariable Long id, @RequestBody ClientDTO clientDTO){
    this.clientService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successful client deletion");
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ClientDTO> updateById (@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
    Address address = Address
    .builder()
    .id(clientDTO.getAddressId())
    .build();

    Client client = Client.builder()
    .cpf(clientDTO.getCpf())
    .build();
    client.setAddress(address);
    client.setEmail(clientDTO.getEmail());
    client.setImageUrl(clientDTO.getImageUrl());
    client.setName(clientDTO.getEmail());
    client.setPhone(clientDTO.getPhone());
    client.setUserName(clientDTO.getUserName());
    client.setPassword(this.encoder.encode(clientDTO.getPassword()));

    client = this.clientService.update(id, client);

    if(client == null){
      throw new EntityNotFoundException(id);
    }
    return  ResponseEntity.ok().body(clientDTO);
  }
}
