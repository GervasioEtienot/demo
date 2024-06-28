package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.PathDTO;
import com.example.demo.entities.Path;

@Repository
public class PathRepository {
    
    private List<Path> pathList = new ArrayList<>();

    public void save(Path path) {
        pathList.add(path);
    }

    public List<Path> listPaths() {
        return pathList;
    }

    public boolean existPath(PathDTO pathDTO) {
        return (pathList.stream().anyMatch(
                path -> path.getId().equals(pathDTO.getId()) || (path.getStations().contains(pathDTO.getSourceId())
                        && path.getStations().contains(pathDTO.getDestinationId()))));
    }
}
