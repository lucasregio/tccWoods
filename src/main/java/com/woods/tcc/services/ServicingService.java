package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.model.Servicing;
import com.woods.tcc.repositories.ServiceRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ServicingService {
  @Autowired
  private ServiceRepository serviceRepository;

  public List<Servicing> findAll() {
    return this.serviceRepository.findAll();
  }

  public Servicing findById(Long id) {
    Optional<Servicing> service = this.serviceRepository.findById(id);
    return service.orElseThrow(() -> new EntityNotFoundException(id));
  }

  public Servicing createServicing(Servicing servicing){
    return this.serviceRepository.save(servicing);
  }

  public void deleteServicing(Long id) {
    try {
      this.serviceRepository.deleteById(id);
    }catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Servicing updateServicing( Long id, Servicing obj) {
    try {
      Servicing entity = this.serviceRepository.getById(id);
      updateData(entity, obj);
      return this.serviceRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public void updateData(Servicing entity, Servicing obj) {
    entity.setDescription(obj.getDescription());
    entity.setImageUrl(obj.getImageUrl());
    entity.setName(obj.getName());
    entity.setType(obj.getType());
  }
}
