package com.woods.tcc.repositories;

import java.util.Optional;

import com.woods.tcc.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
  public Optional<Client> findByEmail(String email);
}
