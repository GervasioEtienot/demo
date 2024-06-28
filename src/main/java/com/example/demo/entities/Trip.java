package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Trip {
    
    private List<Long> path = new ArrayList<>();

    private Double cost;

}
