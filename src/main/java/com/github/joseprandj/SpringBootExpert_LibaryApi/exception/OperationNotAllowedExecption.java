package com.github.joseprandj.SpringBootExpert_LibaryApi.exception;

public class OperationNotAllowedExecption extends RuntimeException{

    public OperationNotAllowedExecption(String message) {
        super(message);
    }
}
