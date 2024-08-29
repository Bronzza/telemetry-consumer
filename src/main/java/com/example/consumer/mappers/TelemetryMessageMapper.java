package com.example.consumer.mappers;

import com.example.consumer.entity.TelemetryEntity;
import model.TelemetryMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TelemetryMessageMapper {

    @Mapping(source = "location", target = "location")
    TelemetryEntity toEntity(TelemetryMessage dto);

    @Mapping(source = "location", target = "location")
    TelemetryMessage toDto(TelemetryEntity entity);
}
