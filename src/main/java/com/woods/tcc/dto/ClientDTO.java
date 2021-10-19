package com.woods.tcc.dto;

import java.io.Serializable;

import com.woods.tcc.model.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private String name;
  private String userName;
  private String email;
  private String phone;
  private String imageUrl;
  private Long addressId;
  private String password;
  private String cpf;

  public ClientDTO(Client client) {
    this.name = client.getName();
    this.userName = client.getUserName();
    this.email = client.getEmail();
    this.phone = client .getPhone();
    this.imageUrl = client.getImageUrl();
    this.addressId = client.getAddress().getId();
    this.password = client.getPassword();
    this.cpf = client.getCpf();
  }
}
