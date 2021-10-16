package com.woods.tcc.repositories;

import com.woods.tcc.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
