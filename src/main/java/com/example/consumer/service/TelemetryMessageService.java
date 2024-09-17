package com.example.consumer.service;

import com.example.consumer.dto.TelemetryDto;
import com.example.consumer.entity.Location;
import com.example.consumer.entity.TelemetryEntity;
import com.example.consumer.mappers.TelemetryEntityToDto;
import com.example.consumer.mappers.TelemetryMessageMapper;
import com.example.consumer.repository.TelemetryMessageRepository;
import model.TelemetryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelemetryMessageService {

    private final TelemetryMessageRepository repository;
    private final TelemetryMessageMapper brokerMapper;
    private final TelemetryEntityToDto webMapper;

    public void saveTelemetryData(TelemetryMessage message) {
        log.info("Proccessing message: {}", message);
        repository.save(brokerMapper.toEntity(message));
        log.info("Message {}, was saved.", message);
    }

    public long getRecordCountByDevice(String deviceName) {
        return repository.countByDeviceName(deviceName);
    }

    public List<TelemetryDto> getDeviceTelemetryById(String deviceId) {
        return repository.findAllByDeviceId(deviceId)
                .stream()
                .map(webMapper::toDto).collect(Collectors.toList());
    }

    public List<TelemetryDto> getAllRecords() {
        return repository.findAll()
                .stream()
                .map(webMapper::toDto)
                .collect(Collectors.toList());
    }

    public Location getLastLocationBeDeviceId(String deviceId) {
        Location result = repository.findFirstByDeviceIdOrderByTimestampDesc(deviceId).orElse(new TelemetryEntity())
                .getLocation();
        return result;
    }

    public List<TelemetryDto> getAllDeviceNames() {
        return repository.findAll()
                .stream()
                .map(webMapper::toDto)
                .collect(Collectors.toList());
    }
}
