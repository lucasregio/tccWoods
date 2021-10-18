package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.repositories.AddressRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.woods.tcc.model.Address;

@Service
public class AddressService {
  @Autowired
  private AddressRepository addressRepository;

  public List<Address> findAll() {
    return this.addressRepository.findAll();
  }

  public Address findById(Long id) {
    Optional<Address> service = this.addressRepository.findById(id);
    return service.orElseThrow();
  }
  public Address createAddress(Address entity){
    return this.addressRepository.save(entity);
  }

  public void deleteAddress (long id){
    try {
      this.addressRepository.deleteById(id);
    } catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Address updateAddress (Long id, Address obj){
    try {
      Address entity = this.addressRepository.getById(id);
      updateData(entity, obj);
      return this.addressRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public void updateData(Address entity, Address obj) {
    entity.setStreet(obj.getStreet());
    entity.setNumber(obj.getNumber());
    entity.setComplement(obj.getComplement());
    entity.setDistrict(obj.getDistrict());
    entity.setCity(obj.getCity());
    entity.setState(obj.getState());
  }
}
