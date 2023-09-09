package com.higortavares.kafkasample.infraestructure;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogProducer {
  @Value("${log.topic}")
  private String topic;
  private final KafkaProducer<String, String> producer;
  public void sendMessage(String key, String message) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
    try {
      /*
       * The send() method returns a Java Future object with RecordMetadata,
       * but since we simply ignore the returned value,
       * we have no way of knowing whether the mes‐ sage was sent successfully or not.
       * This method of sending messages can be used when dropping a message silently is acceptable.
       */
      this.producer.send(record);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
