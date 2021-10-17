package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.repositories.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woods.tcc.model.Address;

@Service
public class AddressService {
  @Autowired
  AddressRepository addressRepository;

  public List<Address> findAll() {
    return this.addressRepository.findAll();
  }

  public Address findById(Long id) {
    Optional<Address> service = this.addressRepository.findById(id);
    return service.orElseThrow();
  }

  public void updateData(Address entity, Address obj) {
    entity.set
    entity.setDescription(obj.getDescription());
    entity.setImageUrl(obj.getImageUrl());
    entity.setName(obj.getName());
    entity.setType(obj.getType());
  }
}
