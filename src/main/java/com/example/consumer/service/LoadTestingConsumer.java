package com.example.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.TelemetryMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Profile("load-test")
@Slf4j
public class LoadTestingConsumer {

    @KafkaListener(topics = "device_data_load_test", groupId = "load-test-group", concurrency = "3")
    public void listen(ConsumerRecord<String, TelemetryMessage> record, Acknowledgment ack) {
        TelemetryMessage message = record.value();
        // Process the message
        log.info("Message recived: {}", message);

        // Manually acknowledge the message if auto-commit is disabled
//        ack.acknowledge();
    }
}
