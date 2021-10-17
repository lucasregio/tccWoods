package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.model.Provider;
import com.woods.tcc.repositories.ProviderRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
  @Autowired
  private ProviderRepository providerRepository;

  public List<Provider> findAll() {
    return this.providerRepository.findAll();
  }

  public Provider findById(Long id) {
    Optional<Provider> oProvider = this.providerRepository.findById(id);
    return oProvider.orElseThrow(() -> new EntityNotFoundException(id));
  }

  public void delete(Long id) {
    try {
      this.providerRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Provider update(Long id, Provider obj) {
    try {
      Provider entity = this.providerRepository.getById(id);
      this.updateData(entity, obj);
      return this.providerRepository. save(entity);
    } catch (Exception e) {
      throw new EntityNotFoundException(id);
    }
  }

  private void updateData(Provider entity, Provider obj) {
    entity.setAddress(obj.getAddress());
    entity.setCnpj(obj.getCnpj());
    entity.setEmail(obj.getEmail());
    entity.setImageUrl(obj.getImageUrl());
    entity.setName(obj.getName());
    entity.setPassword(obj.getPassword());
    entity.setUserName(obj.getUserName());
    entity.setListService(obj.getListService());
  }

}
