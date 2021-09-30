package com.tcc.woods.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tcc.woods.enums.Role;

import lombok.Data;


public @Data class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
	private String login;
	private String password;
	private String email;
	private Role role;
	private String userCode;
}
