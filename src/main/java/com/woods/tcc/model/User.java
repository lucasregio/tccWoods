package com.woods.tcc.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;
  private String name;
  @Column(unique = true)
  private String userName;
  private String password;
  @Column(unique = true)
  private String email;
  private String phone;
  private String imageUrl;
  @OneToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;
}
