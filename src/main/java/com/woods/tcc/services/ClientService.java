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

  public void delete(Long Id) {
    try {

    }catch (EmptyResultDataAccessException e) {
      return new EntityNotFoundException(id);
    }
  }
}
