package com.example.consumer.service;

import model.TelemetryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelemetryMessageService {

    public void saveTelemetryData(TelemetryMessage message) {
        log.info("Message is saved: {}", message);
    }
}
