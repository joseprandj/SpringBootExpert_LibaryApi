package com.github.joseprandj.SpringBootExpert_LibaryApi.exception;


import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.ErrorField;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUntreatedErrors(RuntimeException ex){
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Contact the administrator.",
                List.of()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex){
        return new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "Access Denied!",
                List.of()
        );
    }

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

    @ExceptionHandler(DuplicatedRegisterExcepction.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedRegisterExcepction(DuplicatedRegisterExcepction ex){
        return ErrorResponse.conflict(ex.getMessage());
    }

    @ExceptionHandler(OperationNotAllowedExecption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOperationNotAllowedExecption(OperationNotAllowedExecption ex){
        return ErrorResponse.responseStandard(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerDataIntegrityViolationException(DataIntegrityViolationException ex){
        return new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            "Exclusive restriction violated",
            List.of()
        );
    }

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handlerInvalidFieldException(InvalidFieldException ex){
        return new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "Error validation",
                List.of(new ErrorField(ex.getField(), ex.getMessage()))
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNoSuchElementException(NoSuchElementException ex){
        return new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "No value present",
            List.of()
        );
    }

}
