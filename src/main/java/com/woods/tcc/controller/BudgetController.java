package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.woods.tcc.dto.BudgetDTO;
import com.woods.tcc.model.Budget;
import com.woods.tcc.services.BudgetService;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/budget")
public class BudgetController {

  @Autowired
  private BudgetService budgetService;

  @GetMapping
  public ResponseEntity<List<BudgetDTO>> findAll(){
    List<Budget> listBudget = budgetService.findAll();
    List<BudgetDTO> lBudgetDTOs = listBudget.stream().map(x -> new BudgetDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(lBudgetDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<BudgetDTO> findById(@PathVariable Long id){
    try {
      Budget budget = budgetService.findById(id);
      BudgetDTO budgetDTO = new BudgetDTO(budget);
      return  ResponseEntity.ok().body(budgetDTO);
    }catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @PostMapping(value = "/create/{id}")
  public ResponseEntity<BudgetDTO> create(@RequestBody BudgetDTO budgetDTO, @PathVariable(required = true) Long id) {
    Budget budget = Budget.builder()
    .name(budgetDTO.getName())
    .description(budgetDTO.getDescription())
    .value(budgetDTO.getValue())
    .expirationDate(budgetDTO.getExpirationDate())
    .requestDate(budgetDTO.getRequestDate())
    .build();

    budget = budgetService.createBudget(budget);

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(budget.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new BudgetDTO(budget));
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<String> deletebyId(@PathVariable Long id){
    this.budgetService.deleteBudget(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successful budget deletion");

  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<BudgetDTO> updateById (@PathVariable Long id, @RequestBody BudgetDTO budgetDTO) {
    Budget budget = Budget.builder()
    .name(budgetDTO.getName())
    .description(budgetDTO.getDescription())
    .value(budgetDTO.getValue())
    .expirationDate(budgetDTO.getExpirationDate())
    .requestDate(budgetDTO.getRequestDate())
    .build();
    budget = this.budgetService.updateBudget(id, budget);

    if(budget == null){
      throw new EntityNotFoundException(id);
    }
    return  ResponseEntity.ok().body(budgetDTO);
  }
}
