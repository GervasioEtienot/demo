package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Station;

@Repository
public class StationRepository {
    
    private List<Station> stationList = new ArrayList<>();

    public void save(Station data) {
        stationList.add(data);
    }

    public List<Station> getStations() {
        return stationList;
    }

    public boolean existStation(Long id) {
        return stationList.stream().anyMatch(station -> station.getId().equals(id));
    }
}
