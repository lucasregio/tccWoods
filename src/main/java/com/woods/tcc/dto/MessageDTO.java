package com.woods.tcc.dto;

import java.io.Serializable;

import com.woods.tcc.model.Message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private Long chatId;
  private String description;

  public MessageDTO (Message message){
    this.chatId  = message.getChat().getId();
    this.description = message.getDescription();
  }
}
