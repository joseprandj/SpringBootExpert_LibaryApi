package com.github.joseprandj.SpringBootExpert_LibaryApi.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(
        int status,
        String message,
        List<ErrorField> errorsList
) {

    public static ErrorResponse responseStandard(String message){
        return new ErrorResponse(
            HttpStatus.BAD_GATEWAY.value(),
            message,
            List.of()
        );
    }

    public static ErrorResponse conflict(String message){
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                message,
                List.of()
        );
    }
}
