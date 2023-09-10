package com.higortavares.kafkasample.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;
import payments.avro.Payment;

@Data
public class PaymentRequest {
  private String paymentID = UUID.randomUUID().toString();
  private String sender;
  private String receiver;
  private PaymentMethod paymentMethod;
  private Long amount;
  private String description;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt = LocalDateTime.now();

  public Payment toPayment() {
    Payment payment = new Payment();
    payment.setPaymentID(paymentID);
    payment.setAmount(amount);
    payment.setReceiver(receiver);
    payment.setSender(sender);
    payment.setPaymentMethod(paymentMethod.toString());
    payment.setDescription(description);
    payment.setCreatedAt(createdAt.getNano());
    return payment;
  }
}
