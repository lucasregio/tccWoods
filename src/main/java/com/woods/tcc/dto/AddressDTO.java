package com.woods.tcc.dto;

import com.woods.tcc.model.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

  private String street;
  private String number;
  private String complement;
  private String district;
  private String city;
  private String state;

  public AddressDTO (Address address){
    this.street = address.getStreet();
    this.number = address.getNumber();
    this.complement = address.getComplement();
    this.district = address.getDistrict();
    this.city = address.getCity();
    this.state = address.getState();
  }

}
