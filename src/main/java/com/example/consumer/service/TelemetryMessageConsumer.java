package com.example.consumer.service;

import model.TelemetryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("!load-test")
public class TelemetryMessageConsumer {
    private final TelemetryMessageService telemetryMessageService;


    @KafkaListener(topics = "#{kafkaConfig.getTelemetryTopic()}")
    public void consume(TelemetryMessage message) {
        log.info("Consumed message: {}", message);
        telemetryMessageService.saveTelemetryData(message);
    }
}
