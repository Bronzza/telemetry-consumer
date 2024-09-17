package com.example.consumer.config;

import lombok.extern.slf4j.Slf4j;
import model.TelemetryMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Properties;

@Slf4j
@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TelemetryMessage> kafkaListenerContainerFactory(
            ConsumerFactory<String, TelemetryMessage> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, TelemetryMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        String property = factory.getContainerProperties().getKafkaConsumerProperties().getProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG);
        log.info("Server is: {}", property);
//        log.info("Property is: {}", bootstrapServers);
        return factory;
    }
}
