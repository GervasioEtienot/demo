package com.example.demo.entities;

import com.example.demo.dto.StationDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Station {
    
    private Long id;

    private String name;


    public StationDTO entityToDTO() {
        StationDTO dto = new StationDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        return dto;
    }
}
