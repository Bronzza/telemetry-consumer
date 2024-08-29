package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryMessage {
    private LocalDateTime timestamp;
    private Location location;
    private String deviceId;
//    private LocalDateTime brokerTimestamp;
//    private LocalDateTime dbTimestamp;
}