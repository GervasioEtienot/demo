package com.example.demo.integration;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dto.PathDTO;
import com.example.demo.entities.Path;
import com.example.demo.repositories.PathRepository;

@Configuration
public class TestDataConfig {
    
    @Bean
    CommandLineRunner loadTestData(PathRepository pathRepository) {
        return args -> {
            // Cargar datos de prueba en la memoria
            pathRepository.save(new Path(new PathDTO(1L, 10.0, 1L, 2L)));
            pathRepository.save(new Path(new PathDTO(2L, 10.0, 2L, 3L)));
            pathRepository.save(new Path(new PathDTO(3L, 100.0, 3L, 1L)));
            pathRepository.save(new Path(new PathDTO(4L, 100.0, 4L, 5L)));
        };
    }
}
