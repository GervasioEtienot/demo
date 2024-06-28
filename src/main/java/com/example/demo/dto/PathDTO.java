package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PathDTO {
    
    @NotNull(message = "Path id is required")
    @JsonProperty("path_id")
    private Long id;

    @NotNull(message = "Cost is required")
    @JsonProperty("cost")
    private Double cost;

    @NotNull(message = "Source id is required")
    @JsonProperty("source_id")
    private Long sourceId;
    
    @NotNull(message = "Destination id is required")
    @JsonProperty("destination_id")
    private Long destinationId; 
}
