package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;


import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.ErrorField;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ErrorField> listError = ex.getFieldErrors()
            .stream()
            .map(fe -> new ErrorField(
                fe.getField(),
                fe.getDefaultMessage())
            ).toList();

        return new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Error validation.",
            listError
        );
    }
}
