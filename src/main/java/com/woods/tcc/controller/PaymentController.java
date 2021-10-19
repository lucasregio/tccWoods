package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.woods.tcc.dto.PaymentDTO;
import com.woods.tcc.model.Payment;
import com.woods.tcc.services.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @GetMapping
  public ResponseEntity<List<PaymentDTO>> findAll(){
    List<Payment> listPayment = paymentService.findAll();
    List<PaymentDTO> lPaymentDTOs = listPayment.stream().map(x -> new PaymentDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(lPaymentDTOs);
  }

  @PostMapping(value = "/budget/{id}")
  public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO paymentDTO, @PathVariable(required = true) Long id) {
    Payment payment = Payment.builder()
    .paymentType(paymentDTO.getPaymentType())
    .paymentDate(paymentDTO.getPaymentDate())
    .companyTax(paymentDTO.getCompanyTax())
    .totalValue(paymentDTO.getTotalValue())
    .createdAt(paymentDTO.getCreatedAt())
    .build();

    payment = paymentService.createPayment(payment);

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(payment.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new PaymentDTO(payment));
  }

}
