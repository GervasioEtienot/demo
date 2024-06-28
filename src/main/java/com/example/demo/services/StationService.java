package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StationDTO;
import com.example.demo.entities.Station;
import com.example.demo.exceptions.DuplicateException;
import com.example.demo.repositories.StationRepository;

@Service
public class StationService {
    
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public ResponseEntity<Object> getStations() {
        List<Station> stations = stationRepository.getStations();
        List<StationDTO> dto = stations.stream().map(Station::entityToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<Object> createStation(StationDTO dto) {
        validateStation(dto);
        Station station = new Station();
        station.setId(dto.getId());
        station.setName(dto.getName());
        stationRepository.save(station);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void validateStation(StationDTO dto) {
        if (stationRepository.existStation(dto.getId())) {
            throw new DuplicateException("Station already exists.");
        }
    }
}
