package com.woods.tcc.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.woods.tcc.dto.ClientDTO;
import com.woods.tcc.model.Client;
import com.woods.tcc.repositories.ClientRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private PasswordEncoder encoder;

  @GetMapping
  public ResponseEntity<List<ClientDTO>> findAll() {
    List<Client> lClients = this.clientRepository.findAll();

    if(lClients.isEmpty())
      throw new EntityNotFoundException();

      List<ClientDTO> lClientDTOs = lClients
      .stream()
      .map(x -> new ClientDTO(x))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(lClientDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
    try {
      Client client = this.clientRepository.getById(id);
      ClientDTO clientDTO = new ClientDTO(client);
      return ResponseEntity.ok().body(clientDTO);
    } catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  // @PostMapping
  // public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO) {

  // }

}
