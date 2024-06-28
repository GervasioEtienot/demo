package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PathDTO;
import com.example.demo.services.PathService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/paths")
public class PathController {

    private final PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }
    
    @GetMapping()
    public ResponseEntity<Object> listPaths() {
        return pathService.listPaths();
    }

    @GetMapping("/{source_id}/{destination_id}")
    public ResponseEntity<Object> searchPath(@PathVariable(value = "source_id") Long sourceId,
            @PathVariable(value = "destination_id") Long destinationId) {
        return pathService.searchPath(sourceId, destinationId);
    }

    @PostMapping()
    public ResponseEntity<Object> createPath(@RequestBody @Valid PathDTO dto) {
        return pathService.createPath(dto);
    }
    
}
