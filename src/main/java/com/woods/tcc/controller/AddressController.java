package com.woods.tcc.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.woods.tcc.services.AddressService;
import com.woods.tcc.dto.AddressDTO;
import com.woods.tcc.model.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @GetMapping
  public ResponseEntity<List<AddressDTO>> findAll(){
    List<Address> listAddress = addressService.findAll();
    List<AddressDTO> lAddressDTOs = listAddress.stream().map( x -> new AddressDTO(x)).collect(Collectors.toList());
    if(lAddressDTOs.isEmpty())
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok().body(lAddressDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
    Address address = addressService.findById(id);
    AddressDTO addressDTO = new AddressDTO(address);
    if(address == null)
      return ResponseEntity.notFound().build();
    return  ResponseEntity.ok().body(addressDTO);
  }

}
