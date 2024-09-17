package com.example.consumer.controller;

import com.example.consumer.dto.DeviceNameId;
import com.example.consumer.dto.TelemetryDto;
import com.example.consumer.entity.Location;
import com.example.consumer.service.TelemetryMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
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
        Location result =  telemetryMessageService.getLastLocationBeDeviceId(deviceId);
        log.info("Api called.");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/devices")
    public ResponseEntity<List<TelemetryDto>> getAllRecords() {
        List<TelemetryDto> records = telemetryMessageService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/device_info")
    public ResponseEntity<List<DeviceNameId>> getAllDeviceNames() {
        //TODO: implement endopoint and represent it on ui. For now ui droneId is hardcoded.
        List<DeviceNameId> objects = Collections.emptyList();
        return ResponseEntity.ok(objects);
    }


}
