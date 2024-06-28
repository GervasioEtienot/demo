package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.StationDTO;
import com.example.demo.repositories.StationRepository;

@SpringBootTest(classes = {StationService.class})
class StationServiceTest {
    
    @MockBean
    StationRepository stationRepository;

    @Autowired
    StationService stationService;
    
    @Test
    void createStation_ok() {
        StationDTO dto = new StationDTO();
        dto.setId(1L);
        dto.setName("A");
        ResponseEntity<Object> response = stationService.createStation(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetStations() {

    }
}
