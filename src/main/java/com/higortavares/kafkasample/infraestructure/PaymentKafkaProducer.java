package com.higortavares.kafkasample.infraestructure;

import com.higortavares.kafkasample.controller.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payments.avro.Payment;

@Service
@RequiredArgsConstructor
public class PaymentKafkaProducer {
  @Value("${payment.topic}")
  private String topic;
  private final KafkaProducer<String, Payment> producer;

  public void sendMessage(String key, PaymentRequest message) {
    ProducerRecord<String, Payment> record = new ProducerRecord<>(topic, key, message.toPayment());
    try {
      //send the message synchronously
      this.producer.send(record).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
