package com.woods.tcc.repositories;

import com.woods.tcc.model.Servicing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Servicing, Long>{

}
