package com.higortavares.kafkasample.infraestructure;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import payments.avro.Payment;

@Configuration
public class KafkaProducerConnection {
  @Bean("myProducer")
  public static KafkaProducer<String, String> kafkaProducerFactory() {
    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", "localhost:29092");
    kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    return new KafkaProducer<String, String>(kafkaProps);
  }

  @Value("${schema.url}")
  private String schemaUrl;

  @Bean("paymentProducer")
  public KafkaProducer<String, Payment> avroProducerFactory() {
    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", "localhost:29092");
    kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    kafkaProps.put("value.serializer","io.confluent.kafka.serializers.KafkaAvroSerializer");
    kafkaProps.put("schema.registry.url", schemaUrl);
    return new KafkaProducer<String, Payment>(kafkaProps);
  }
}
