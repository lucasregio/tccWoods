package com.woods.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;


@MappedSuperclass
@Data
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String imageUrl;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
