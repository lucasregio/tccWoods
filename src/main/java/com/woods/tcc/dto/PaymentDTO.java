package com.woods.tcc.dto;

import java.io.Serializable;
import java.time.Instant;

import com.woods.tcc.model.Payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO implements Serializable{

  private static final long serialVersionUID = 1L;

  private String paymentType;
  private String totalValue;
  private String companyTax;
  private Instant paymentDate;
  private Instant createdAt;

  public PaymentDTO(Payment payment) {
    this.paymentType = payment.getPaymentType();
    this.totalValue = payment.getTotalValue();
    this.companyTax = payment.getCompanyTax();
    this.paymentDate = payment.getPaymentDate();
    this.createdAt = payment.getCreatedAt();
  }

}
