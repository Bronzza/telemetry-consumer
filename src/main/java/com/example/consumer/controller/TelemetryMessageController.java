package com.example.consumer.controller;

import com.example.consumer.dto.TelemetryDto;
import com.example.consumer.entity.Location;
import com.example.consumer.service.TelemetryMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
public class TelemetryMessageController {

    private final TelemetryMessageService telemetryMessageService;

    @GetMapping("/device-records")
    public ResponseEntity<Long> getRecordCountByDevice(@RequestParam("deviceName") String deviceName) {
        long count = telemetryMessageService.getRecordCountByDevice(deviceName);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<TelemetryDto>> getTelemetryByDeviceId(@PathVariable("deviceId") String deviceId) {
        List<TelemetryDto> result = telemetryMessageService.getDeviceTelemetryById(deviceId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/device/location/{deviceId}")
    public ResponseEntity<Location> getLocationByDeviceId(@PathVariable("deviceId") String deviceId) {
        Location result =  telemetryMessageService.getLocationBeDeviceId(deviceId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/devices")
    public ResponseEntity<List<TelemetryDto>> getAllRecords() {
        List<TelemetryDto> records = telemetryMessageService.getAllRecords();
        return ResponseEntity.ok(records);
    }
}
