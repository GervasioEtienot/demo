package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.StationDTO;
import com.example.demo.exceptions.DuplicateException;
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
    void createStation_invalidStation() {
        StationDTO dto = new StationDTO();
        dto.setId(1L);
        dto.setName("A");
        
        when(stationRepository.existStation(1L)).thenReturn(true);

        Exception exception = assertThrows(DuplicateException.class, () -> stationService.createStation(dto));
        assertEquals("Station already exists.", exception.getMessage());

    }
}
