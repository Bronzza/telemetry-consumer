package com.example.consumer.controller;

import com.example.consumer.dto.TelemetryDto;
import com.example.consumer.entity.Location;
import com.example.consumer.service.TelemetryMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TelemetryMessageController.class)
public class TelemetryMessageControllerTest {

    public static final String TEST_ID = "testId";
    public static final String TEST_NAME = "testName";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    public static final Location LOCATION = new Location(LATITUDE, LONGITUDE);
    public static final String PARAM_DEVICE_NAME = "deviceName";
    public static final String PARAM_DEVICE_NAME_VALUE = "device1";
    public static final long TEST_COUNT = 10L;
    public static final String EXPECTED_COUNT = "10";
    public static final String PATH_GET_DEVICE_BY_ID = "/api/telemetry/device/{deviceId}";
    public static final String PATH_GET_ALL_RECORDS = "/api/telemetry/devices";
    public static final String PATH_GET_LOCATION_BY_ID = "/api/telemetry/device/location/{deviceId}";
    public static final String PATH_GET_COUNT_BY_NAME = "/api/telemetry/device-records";

    @MockBean
    private TelemetryMessageService telemetryMessageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRecordCountByDevice() throws Exception {
        when(telemetryMessageService.getRecordCountByDevice(PARAM_DEVICE_NAME_VALUE)).thenReturn(TEST_COUNT);

        mockMvc.perform(get(PATH_GET_COUNT_BY_NAME)
                        .param(PARAM_DEVICE_NAME, PARAM_DEVICE_NAME_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(EXPECTED_COUNT));
    }

    @Test
    public void testGetTelemetryByDeviceId() throws Exception {
        List<TelemetryDto> dtoList = creteTestData();
        when(telemetryMessageService.getDeviceTelemetryById(anyString())).thenReturn(dtoList);

        mockMvc.perform(get(PATH_GET_DEVICE_BY_ID, TEST_ID))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).contains(TEST_ID));
    }

    @Test
    public void testGetAllRecords() throws Exception {
        List<TelemetryDto> dtoList = creteTestData();
        when(telemetryMessageService.getAllRecords()).thenReturn(dtoList);

        mockMvc.perform(get(PATH_GET_ALL_RECORDS))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).contains(TEST_ID));
    }

    @Test
    public void testGetLocationByDeviceId() throws Exception {
        when(telemetryMessageService.getLocationBeDeviceId(TEST_ID)).thenReturn(LOCATION);

        mockMvc.perform(get(PATH_GET_LOCATION_BY_ID, TEST_ID))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).contains(LATITUDE))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).contains(LONGITUDE));
    }

    private static List<TelemetryDto> creteTestData() {
        TelemetryDto dto = new TelemetryDto();
        dto.setDeviceId(TEST_ID);
        dto.setDeviceName(TEST_NAME);
        dto.setTimestamp(LocalDateTime.now());
        dto.setLocation(LOCATION);
        List<TelemetryDto> dtoList = Arrays.asList(dto);
        return dtoList;
    }
}