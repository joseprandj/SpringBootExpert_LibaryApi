package com.github.joseprandj.SpringBootExpert_LibaryApi.exception;

import lombok.Getter;

@Getter
public class InvalidFieldException extends RuntimeException {

    private String field;

    public InvalidFieldException(String filed, String message) {
        super(message);
        this.field = filed;
    }
}