package com.example.demo.entities;

import java.util.Arrays;
import java.util.List;

import com.example.demo.dto.PathDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Path {
    
    private Long id;

    private Double cost;

    private List<Long> stations;
    
    public Path(PathDTO pathDTO) {
        this.id = pathDTO.getId();
        this.cost = pathDTO.getCost();
        this.stations = Arrays.asList(pathDTO.getSourceId(), pathDTO.getDestinationId());
    }
    
    public PathDTO entityToDTO() {
        PathDTO dto = new PathDTO();
        dto.setId(this.id);
        dto.setCost(this.cost);
        dto.setSourceId(this.stations.get(0));
        dto.setDestinationId(this.stations.get(1));
        return dto;
    }
}
