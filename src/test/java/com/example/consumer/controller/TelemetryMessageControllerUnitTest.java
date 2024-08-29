package com.example.consumer.controller;

import com.example.consumer.dto.TelemetryDto;
import com.example.consumer.entity.Location;
import com.example.consumer.service.TelemetryMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.example.consumer.controller.TelemetryMessageControllerTest.TEST_ID;
import static com.example.consumer.controller.TelemetryMessageControllerTest.TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TelemetryMessageControllerUnitTest {
    public static final long EXPECTED_COUNT = 10L;
    @InjectMocks
    private TelemetryMessageController controllerUnderTest;

    @Mock
    private TelemetryMessageService telemetryMessageService;

    @Test
    public void testGetRecordCountByDevice() throws Exception {
        given(telemetryMessageService.getRecordCountByDevice(anyString()))
                .willReturn(EXPECTED_COUNT);
        ResponseEntity<Long> result = controllerUnderTest.getRecordCountByDevice(TEST_NAME);
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        then(telemetryMessageService).should(times(1)).getRecordCountByDevice(TEST_NAME);
    }

    @Test
    public void testGetTelemetryByDeviceId() throws Exception {
        given(telemetryMessageService.getDeviceTelemetryById(TEST_ID))
                .willReturn(creteTestData());
        ResponseEntity<List<TelemetryDto>> result = controllerUnderTest.getTelemetryByDeviceId(TEST_ID);
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        then(telemetryMessageService).should(times(1)).getDeviceTelemetryById(TEST_ID);

    }

    @Test
    public void testGetAllRecords() throws Exception {
        given(telemetryMessageService.getAllRecords())
                .willReturn(creteTestData());
        ResponseEntity<List<TelemetryDto>> result = controllerUnderTest.getAllRecords();
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        then(telemetryMessageService).should(times(1)).getAllRecords();
    }

    @Test
    public void testGetLocationByDeviceId() throws Exception {
        TelemetryDto testTelemetry = creteTestData().getFirst();
        given(telemetryMessageService.getLocationBeDeviceId(anyString()))
                .willReturn(testTelemetry.getLocation());
        ResponseEntity<Location> result = controllerUnderTest.getLocationByDeviceId(TEST_ID);
        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        then(telemetryMessageService).should(times(1)).getLocationBeDeviceId(TEST_ID);
    }

    private static List<TelemetryDto> creteTestData() {
        TelemetryDto dto = new TelemetryDto();
        dto.setDeviceId(TelemetryMessageControllerTest.TEST_ID);
        dto.setDeviceName(TEST_NAME);
        dto.setTimestamp(LocalDateTime.now());
        dto.setLocation(TelemetryMessageControllerTest.LOCATION);
        List<TelemetryDto> dtoList = Arrays.asList(dto);
        return dtoList;
    }
}