package com.woods.tcc.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Budget implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;
  private String name;
  private String description;
  private String value;
  private Date expirationDate;
  private Date requestDate;
  @ManyToOne
  @JoinColumn(name = "client_id", nullable=false)
  private Client client;
  @ManyToOne
  @JoinColumn(name = "servicing_id", nullable=false)
  private Servicing servicing;
}
