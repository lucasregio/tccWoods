package com.woods.tcc.dto;

import java.io.Serializable;

import com.woods.tcc.model.Chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private Long clientId;
  private Long providerId;
  private Long budgetId;

  public ChatDTO (Chat chat){
    this.clientId = chat.getClientChat().getId();
    this.providerId = chat.getProvider().getId();
    this.budgetId = chat.getBudget().getId();
  }
}
