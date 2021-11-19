package com.woods.tcc.dto;

import java.io.Serializable;

import com.woods.tcc.model.Provider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProviderDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String userName;
  private String email;
  private String phone;
  private String imageUrl;
  private Long addressId;
  private String password;
  private String cnpj;
  private Long evaluation;

  public ProviderDTO(Provider provider) {
    this.id = provider.getId();
    this.name = provider.getName();
    this.userName = provider.getUserName();
    this.email = provider.getEmail();
    this.phone = provider .getPhone();
    this.imageUrl = provider.getImageUrl();
    this.addressId = provider.getAddress().getId();
    this.password = provider.getPassword();
    this.cnpj = provider.getCnpj();
    this.evaluation = provider.getRating();
  }
}
