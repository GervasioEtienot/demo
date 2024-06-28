package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.PathDTO;
import com.example.demo.entities.Path;
import com.example.demo.entities.Station;
import com.example.demo.entities.Trip;
import com.example.demo.exceptions.PathNotFoundException;
import com.example.demo.repositories.PathRepository;

@SpringBootTest(classes = {PathService.class})
class PathServiceTest {
    
    @MockBean
    PathRepository pathRepository;

    @Autowired
    PathService pathService;

    private List<Path> paths;
    
    @BeforeEach
    public void setup() {
        Station station1 = new Station();
        station1.setId(1L);
        station1.setName("A");
        Station station2 = new Station();
        station2.setId(1L);
        station2.setName("B");
        Station station3 = new Station();
        station3.setId(1L);
        station3.setName("C");

        Path path1 = new Path(new PathDTO(1L, 10.0, 1L, 2L));
        Path path2 = new Path(new PathDTO(2L, 10.0, 2L, 3L));
        Path path3 = new Path(new PathDTO(3L, 100.0, 3L, 1L));
        Path path4 = new Path(new PathDTO(4L, 100.0, 4L, 5L));

        paths = Arrays.asList(path1, path2, path3, path4);
    }

    @Test
    void searchPath_ok() {
        when(pathRepository.listPaths()).thenReturn(paths);
        ResponseEntity<Object> response = pathService.searchPath(1L, 3L);
        Trip trip = (Trip) response.getBody();

        assertEquals(20.0, trip.getCost());
    }

    @Test
    void searchPath_noPath() {
        when(pathRepository.listPaths()).thenReturn(paths);
        Exception exception = assertThrows(PathNotFoundException.class, () -> pathService.searchPath(1L, 5L));
        
        assertEquals("There isn't path between stations", exception.getMessage());
    }
}
