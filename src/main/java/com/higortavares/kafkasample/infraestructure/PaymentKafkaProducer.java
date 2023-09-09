package com.higortavares.kafkasample.infraestructure;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentKafkaProducer {
  @Value("${payment.topic}")
  private String topic;
  private final KafkaProducer<String, String> producer;

  public void sendMessage(String key, String message) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
    try {
      this.producer.send(record);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
