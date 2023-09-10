package com.higortavares.kafkasample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higortavares.kafkasample.controller.request.PaymentRequest;
import com.higortavares.kafkasample.infraestructure.LogProducer;
import com.higortavares.kafkasample.infraestructure.PaymentKafkaProducer;
import java.util.UUID;
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
  private final LogProducer logs;
  @PostMapping("/")
  public ResponseEntity<PaymentRequest> pay(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException {
    logs.sendMessage(UUID.randomUUID().toString(), String.format("New payments order ID [%s]. amount [%d]...", paymentRequest.getPaymentID(), paymentRequest.getAmount()));
    producer.sendMessage(paymentRequest.getPaymentID(), paymentRequest);
    logs.sendMessage(UUID.randomUUID().toString(), String.format("Payments order received with success [%s]...", paymentRequest.getPaymentID()));
    return ResponseEntity.ok(paymentRequest);
  }
}
