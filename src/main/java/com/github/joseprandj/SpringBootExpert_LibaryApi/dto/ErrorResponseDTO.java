package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponseDTO(
        int status,
        String message,
        List<ErrorFieldDTO> errorsList
) {

    public static ErrorResponseDTO responseStandard(String message){
        return new ErrorResponseDTO(
            HttpStatus.BAD_GATEWAY.value(),
            message,
            List.of()
        );
    }

    public static ErrorResponseDTO conflict(String message){
        return new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                message,
                List.of()
        );
    }
}
