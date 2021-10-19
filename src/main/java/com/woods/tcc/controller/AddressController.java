package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.woods.tcc.services.AddressService;
import com.woods.tcc.services.exceptions.EntityNotFoundException;
import com.woods.tcc.dto.AddressDTO;
import com.woods.tcc.model.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @GetMapping
  public ResponseEntity<List<AddressDTO>> findAll(){
    List<Address> listAddress = addressService.findAll();

    if(listAddress.isEmpty())
      throw new EntityNotFoundException(0);

    List<AddressDTO> lAddressDTOs = listAddress
      .stream()
      .map( x -> new AddressDTO(x))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(lAddressDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
    try {
      Address address = addressService.findById(id);
      AddressDTO addressDTO = new AddressDTO(address);
      return  ResponseEntity.ok().body(addressDTO);
    }catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @PostMapping
  public ResponseEntity<AddressDTO> create(@RequestBody AddressDTO addressDto) {
    Address address = Address.builder()
      .city(addressDto.getCity())
      .complement(addressDto.getComplement())
      .district(addressDto.getDistrict())
      .number(addressDto.getNumber())
      .street(addressDto.getCity())
      .state(addressDto.getState())
      .build();

    address = addressService.createAddress(address);

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(address.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new AddressDTO(address));
  }

}
