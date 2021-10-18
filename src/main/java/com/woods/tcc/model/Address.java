package com.woods.tcc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
    @OneToOne(mappedBy = "address")
    private User user;

    private Address(AddressBuilder builder) {
      this.id = builder.id;
      this.street = builder.street;
      this.number = builder.number;
      this.complement = builder.complement;
      this.district = builder.district;
      this.city = builder.city;
      this.state = builder.state;
      this.user = builder.user;
    }

    public static class AddressBuilder {
      private Long id;
      private String street;
      private String number;
      private String complement;
      private String district;
      private String city;
      private String state;
      private User user;

      public AddressBuilder() {

      }
      public AddressBuilder id(Long id) {
        this.id = id;
        return this;
      }

      public AddressBuilder street(String street) {
        this.street = street;
        return this;
      }

      public AddressBuilder number(String number) {
        this.number = number;
        return this;
      }

      public AddressBuilder complement(String complement) {
        this.complement = complement;
        return this;
      }

      public AddressBuilder district(String district) {
        this.district = district;
        return this;
      }

      public AddressBuilder city(String city) {
        this.city = city;
        return this;
      }

      public AddressBuilder state(String state) {
        this.state = state;
        return this;
      }

      public AddressBuilder user(User user){
        this.user = user;
        return this;
      }

      public Address build(){
        return new Address(this);
      }

    }
}
