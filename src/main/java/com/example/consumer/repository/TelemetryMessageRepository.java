package com.example.consumer.repository;

import com.example.consumer.entity.TelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelemetryMessageRepository extends JpaRepository<TelemetryEntity, Long> {
    long countByDeviceName(String deviceId);

    List<TelemetryEntity> findAllByDeviceId(String deviceId);

    Optional<TelemetryEntity> findByDeviceId(String deviceId);

    Optional<TelemetryEntity> findFirstByDeviceIdOrderByTimestampDesc(String deviceId);
}
