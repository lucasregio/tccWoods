package com.woods.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public  @Data class Address implements Serializable {
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
    @OneToOne
    private User user;
}
