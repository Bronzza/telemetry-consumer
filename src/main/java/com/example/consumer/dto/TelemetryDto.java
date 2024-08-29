package com.example.consumer.dto;

import com.example.consumer.entity.Location;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDto {

    private LocalDateTime timestamp;
    private String deviceId;
    private String deviceName;

    @Embedded
    private Location location;
}
