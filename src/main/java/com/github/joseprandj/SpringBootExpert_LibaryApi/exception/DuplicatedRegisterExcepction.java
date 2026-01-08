package com.github.joseprandj.SpringBootExpert_LibaryApi.exception;

public class DuplicatedRegisterExcepction extends RuntimeException{
    public DuplicatedRegisterExcepction(String message) {
        super(message);
    }
}
