package com.higortavares.kafkasample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higortavares.kafkasample.controller.request.PaymentRequest;
import com.higortavares.kafkasample.infraestructure.PaymentKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentKafkaProducer producer;
  private final ObjectMapper objectMapper;
  @PostMapping("/")
  public ResponseEntity<PaymentRequest> pay(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException {
    producer.sendMessage(paymentRequest.getPaymentID(), objectMapper.writeValueAsString(paymentRequest));
    return ResponseEntity.ok(paymentRequest);
  }
}
