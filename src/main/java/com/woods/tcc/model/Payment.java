package com.woods.tcc.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Payment implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;
  @Column(nullable = false)
  private String paymentType;
  @Column(nullable = false)
  private String totalValue;
  @Column(nullable = false)
  private String companyTax;
  private Instant paymentDate;
  @Column(unique = true, nullable = false)
  private Instant createdAt;
  @OneToOne(mappedBy = "payment")
  private Budget budget;
}
