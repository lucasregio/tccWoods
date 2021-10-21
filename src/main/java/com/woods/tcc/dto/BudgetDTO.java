package com.woods.tcc.dto;

import java.io.Serializable;
import java.time.Instant;

import com.woods.tcc.model.Budget;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private String name;
  private String description;
  private Long value;
  private Instant expirationDate;
  private Instant requestDate;
  private Long clientId;
  private Long servicingId;
  private Long paymentId;

  public BudgetDTO (Budget budget){
    this.name = budget.getName();
    this.description = budget.getDescription();
    this.value = budget.getValue();
    this.expirationDate = budget.getExpirationDate();
    this.requestDate = budget.getRequestDate();
    this.clientId = budget.getClient().getId();
    this.servicingId = budget.getServicing().getId();
    this.paymentId = budget.getPayment().getId();
  }
}
