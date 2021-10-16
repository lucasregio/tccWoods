package com.woods.model;

import java.io.Serializable;

import javax.persistence.*;

import com.woods.enums.RoleEnum;

import lombok.Data;

@Entity
public @Data abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String imageUrl;
    @OneToOne
    @JoinColumn()
    private Address address;
}
