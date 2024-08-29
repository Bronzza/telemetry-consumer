package com.example.consumer.mappers;

import com.example.consumer.dto.TelemetryDto;
import com.example.consumer.entity.TelemetryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TelemetryEntityToDto {
    @Mapping(source = "location", target = "location")
    TelemetryDto toDto(TelemetryEntity entity);

    @Mapping(source = "location", target = "location")
    TelemetryEntity toEntity(TelemetryDto dto);
}
