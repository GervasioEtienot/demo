package com.example.demo.exceptions;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    public static final Logger LOG = LoggerFactory.getLogger(GlobalErrorHandler.class);
    

    @ExceptionHandler(value = Exception.class)
	protected ResponseEntity<Object> handleException(Exception e) {
		logger.error("Exception", e);
        ErrorDTO error = new ErrorDTO("An unexpected error has occurred", new Date());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(value = IllegalArgumentException.class)
	protected ResponseEntity<Object> handleException(IllegalArgumentException e) {
		ErrorDTO error = new ErrorDTO(e.getMessage(), new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler(value = DuplicateException.class)
    protected ResponseEntity<Object> handleException(DuplicateException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = PathNotFoundException.class)
    protected ResponseEntity<Object> handleException(PathNotFoundException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
