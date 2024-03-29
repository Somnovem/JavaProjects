package com.itstep.sound.config.exceptions;

import com.itstep.sound.dtos.exceptions.ExceptionResponseDTO;
import com.itstep.sound.exceptions.StatusException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StatusException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(StatusException ex) {
        ExceptionResponseDTO errorResponseDTO = new ExceptionResponseDTO(ex);
        HttpStatus status = ex.getStatus();
        return ResponseEntity.status(status).body(errorResponseDTO);
    }
}