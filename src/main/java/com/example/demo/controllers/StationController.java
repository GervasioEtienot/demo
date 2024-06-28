package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StationDTO;
import com.example.demo.services.StationService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Validated
@RequestMapping("/stations")
public class StationController {
    
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping()
    public ResponseEntity<Object> getStations() {
        return stationService.getStations();
    }

    @PostMapping()
    public ResponseEntity<Object> createStation(@RequestBody @Valid StationDTO dto) {
        return stationService.createStation(dto);
    }
    
    
}
