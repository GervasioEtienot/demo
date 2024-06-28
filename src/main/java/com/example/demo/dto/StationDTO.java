package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationDTO {
    
    @NotNull(message = "Station id is required")
    @JsonProperty("station_id")
    private Long id;

    @NotNull(message = "Name is required")
    private String name;

}
