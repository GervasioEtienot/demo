package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PathDTO;
import com.example.demo.entities.Trip;
import com.example.demo.exceptions.DuplicateException;
import com.example.demo.exceptions.PathNotFoundException;
import com.example.demo.entities.Path;
import com.example.demo.repositories.PathRepository;

@Service
public class PathService {
    
    private final PathRepository pathRepository;

    public PathService(PathRepository pathRepository) {
        this.pathRepository = pathRepository;
    }

    public ResponseEntity<Object> createPath(PathDTO dto) {
        validatePath(dto);
        Path path = new Path(dto);
        pathRepository.save(path);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Object> listPaths() {
        List<Path> paths = pathRepository.listPaths();
        List<PathDTO> response = paths.stream().map(Path::entityToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> searchPath(Long sourceId, Long destinationId) {
        List<Trip> trips = new ArrayList<>();

        List<Path> paths = pathRepository.listPaths();
        Trip trip = new Trip(Arrays.asList(sourceId), 0.0);
        findPath(trips, trip, destinationId, paths);
        Trip selectedTrip = selectCheapest(trips);
        return new ResponseEntity<>(selectedTrip, HttpStatus.OK);
    }

    private void findPath(List<Trip> trips, Trip trip, Long destinationId, List<Path> paths) {
        for (Path path : paths) {
            Long nextStation = getNextStation(trip, path);
            if (nextStation == null)
                continue;

            List<Long> partialTrip = new ArrayList<>();                
            partialTrip.addAll(trip.getPath());
            partialTrip.add(nextStation);
            Trip completedTrip = new Trip(partialTrip, trip.getCost() + path.getCost());
            
            if (nextStation.equals(destinationId)) {
                trips.add(completedTrip);
            } else {
                findPath(trips, completedTrip, destinationId, paths);
            }
            
        }
    }

    private Long getNextStation(Trip trip, Path path) {
        List<Long> nextStation = path.getStations().stream()
            .filter(st -> !st.equals(trip.getPath().get(trip.getPath().size()-1)))
            .collect(Collectors.toList());
        return (nextStation.size() == 1 && trip.getPath().stream().noneMatch(station -> nextStation.get(0).equals(station))) 
           ? nextStation.get(0)
           : null;
    }

    private Trip selectCheapest(List<Trip> trips) {
        return trips.stream().min(Comparator.comparing(Trip::getCost)).orElseThrow(() -> new PathNotFoundException("There isn't path between stations"));
    }

    private void validatePath(PathDTO pathDTO) {
        if (pathRepository.existPath(pathDTO)) {
            throw new DuplicateException("path_id or path between stations already exists.");
        }
    }

}
