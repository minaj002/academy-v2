package com.academy.infrastructure.exceptions;

import com.academy.rest.ResponseWrapper;
import com.academy.security.exceptions.SessionTerminatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseWrapper handle(ValidationException ex) {
        return ResponseWrapper.builder().errors(ex.getErrors()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseWrapper handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(ValidationError.builder().field(ex.getBindingResult().getFieldError().getField()).message(ex.getBindingResult().getFieldError().getDefaultMessage()).build());
        return ResponseWrapper.builder().errors(errors).build();
    }

    @ExceptionHandler(SessionTerminatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseWrapper handleMailException(SessionTerminatedException ex) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(ValidationError.builder().field("session").message(ex.getLocalizedMessage()).build());
        return ResponseWrapper.builder().errors(errors).build();
    }

}
