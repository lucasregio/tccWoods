package com.woods.tcc.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Budget implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;
  private String name;
  private String description;
  private Long value;
  private Instant expirationDate;
  private Instant requestDate;
  @ManyToOne
  @JoinColumn(name = "client_id", nullable=false)
  private Client client;
  @ManyToOne
  @JoinColumn(name = "servicing_id", nullable=false)
  private Servicing servicing;
  @OneToOne
  @JoinColumn(name = "payment_id", referencedColumnName = "id")
  private Payment payment;
}
