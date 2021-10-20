package com.woods.tcc.repositories;

import java.util.Optional;

import com.woods.tcc.model.Provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
  public Optional<Provider> findByEmail(String email);
}
