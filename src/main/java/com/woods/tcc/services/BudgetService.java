package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.model.Budget;
import com.woods.tcc.repositories.BudgetRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
  @Autowired
  private BudgetRepository budgetRepository;

  public List<Budget> findAll() {
    return this.budgetRepository.findAll();
  }

  public Budget findById(Long id) {
    Optional<Budget> service = this.budgetRepository.findById(id);
    return service.orElseThrow();
  }
  public Budget createBudget(Budget entity){
    return this.budgetRepository.save(entity);
  }

  public void deleteBudget (long id){
    try {
      this.budgetRepository.deleteById(id);
    } catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Budget updateBudget (Long id, Budget obj){
    try {
      Budget entity = this.budgetRepository.getById(id);
      updateData(entity, obj);
      return this.budgetRepository.save(entity);
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
