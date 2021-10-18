package com.woods.tcc.dto;

import com.woods.tcc.model.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
  private Long id;
  private String name;
  private String userName;
  private String email;
  private String phone;
  private String imageUrl;
  private Long addresId;
  private String password;

  public ClientDTO(Client client) {
    this.name = client.getName();
    this.userName = client.getUserName();
    this.email = client.getEmail();
    this.phone = client .getPhone();
    this.imageUrl = client.getImageUrl();
    this.addresId = client.getAddress().getId();
    this.password = client.getPassword();
  }
}
