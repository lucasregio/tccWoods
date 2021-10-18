package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.repositories.BudgetRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.woods.tcc.model.Budget;

@Service
public class BudgetService {
  @Autowired
  private BudgetRepository addressRepository;

  public List<Budget> findAll() {
    return this.addressRepository.findAll();
  }

  public Budget findById(Long id) {
    Optional<Budget> service = this.addressRepository.findById(id);
    return service.orElseThrow();
  }
  public Budget createBudget(Budget entity){
    return this.addressRepository.save(entity);
  }

  public void deleteBudget (long id){
    try {
      this.addressRepository.deleteById(id);
    } catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Budget updateBudget (Long id, Budget obj){
    try {
      Budget entity = this.addressRepository.getById(id);
      updateData(entity, obj);
      return this.addressRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public void updateData(Budget entity, Budget obj) {
    entity.setName(obj.getName());
    entity.setDescription(obj.getDescription());
    entity.setValue(obj.getValue());
    entity.setExpirationDate(obj.getExpirationDate());
    entity.setRequestDate(obj.getRequestDate());
  }

}
