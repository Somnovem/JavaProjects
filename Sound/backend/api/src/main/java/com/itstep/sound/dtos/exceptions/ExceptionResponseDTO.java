package com.itstep.sound.dtos.exceptions;

import lombok.Data;

@Data
public class ExceptionResponseDTO {

    private final String status;
    private final String message;

    public ExceptionResponseDTO(Exception ex) {
        this.status = ex.getClass().toString();
        this.message = ex.getMessage();
    }
}