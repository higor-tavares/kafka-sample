package com.higortavares.kafkasample.infraestructure;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConnection {
  @Bean("myProducer")
  private static KafkaProducer<String, String> kafkaProducerFactory() {
    Properties kafkaProps = new Properties();
    kafkaProps.put("bootstrap.servers", "localhost:29092");
    kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
    kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    return new KafkaProducer<String, String>(kafkaProps);
  }
}
