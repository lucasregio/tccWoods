package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.repositories.PaymentRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.woods.tcc.model.Payment;

@Service
public class PaymentService {
  @Autowired
  private PaymentRepository paymentRepository;

  public List<Payment> findAll() {
    return this.paymentRepository.findAll();
  }

  public Payment findById(Long id) {
    Optional<Payment> service = this.paymentRepository.findById(id);
    return service.orElseThrow();
  }
  public Payment createPayment(Payment entity){
    return this.paymentRepository.save(entity);
  }

  public void deletePayment (long id){
    try {
      this.paymentRepository.deleteById(id);
    } catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Payment updatePayment (Long id, Payment obj){
    try {
      Payment entity = this.paymentRepository.getById(id);
      updateData(entity, obj);
      return this.paymentRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public void updateData(Payment entity, Payment obj) {
    entity.setPaymentType(obj.getPaymentType());
    entity.setTotalValue(obj.getTotalValue());
    entity.setCompanyTax(obj.getCompanyTax());
    entity.setPaymentDate(obj.getPaymentDate());
    entity.setCreatedAt(obj.getCreatedAt());
  }
}
