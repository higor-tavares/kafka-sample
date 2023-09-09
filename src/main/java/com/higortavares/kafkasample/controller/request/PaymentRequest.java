package com.higortavares.kafkasample.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

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
}
