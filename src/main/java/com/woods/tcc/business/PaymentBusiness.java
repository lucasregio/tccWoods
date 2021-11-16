package com.woods.tcc.business;

import java.time.Instant;

import com.woods.tcc.model.Payment;

public class PaymentBusiness {
  public static final Double COMPANY_TAX = 0.1D;
  public void pay(Payment payment){
    payment.setPaymentDate(Instant.now());
  }
}
