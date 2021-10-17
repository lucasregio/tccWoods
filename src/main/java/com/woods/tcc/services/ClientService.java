package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.model.Client;
import com.woods.tcc.repositories.ClientRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  @Autowired
  ClientRepository clientRepository;

  public List<Client> findAll() {
    return this.clientRepository.findAll();
  }

  public Client findById(Long id) {
    Optional<Client> cOptional = this.clientRepository.findById(id);
    return cOptional.orElseThrow(() -> new EntityNotFoundException(id));
  }

  public Client create(Client client) {
    return this.clientRepository.save(client);
  }

  public void delete(Long id) {
    try {
      this.clientRepository.deleteById(id);
    }catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Client update(Long id, Client obj) {
    try {
      Client entity = this.clientRepository.getById(id);
      this.updateData(entity, obj);
      return this.clientRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public void updateData(Client entity, Client obj) {
    entity.setAddress(obj.getAddress());
    entity.setBudget(obj.getBudget());
    entity.setCpf(obj.getCpf());
    entity.setEmail(obj.getEmail());
    entity.setImageUrl(obj.getImageUrl());
    entity.setUserName(obj.getUserName());
    entity.setPhone(obj.getPhone());
    entity.setName(obj.getName());
  }
}
