package com.woods.tcc.dto;

import java.io.Serializable;
import java.util.List;

import com.woods.tcc.model.Budget;
import com.woods.tcc.model.Servicing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ServicingDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private String name;
  private String description;
  private String type;
  private String imageUrl;
  private Long providerId;
  private List<Budget> listBudgets;

  public ServicingDTO(Servicing servicing){
    this.name = servicing.getName();
    this.description = servicing.getDescription();
    this.type = servicing.getType();
    this.imageUrl = servicing.getImageUrl();
    this.providerId = servicing.getProvider().getId();
    this.listBudgets = servicing.getListBudgets();
  }
}
