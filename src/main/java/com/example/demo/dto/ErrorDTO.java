package com.example.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorDTO {
    
    private Date timestamp;

    private String message;

    public ErrorDTO(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
