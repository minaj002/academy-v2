package com.weststein.infrastructure.exceptions;

import com.weststein.security.exceptions.SessionTerminatedException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
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
    public ValidationErrors handle(ValidationException ex) {
        return ValidationErrors.builder().errors(ex.getErrors()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(ValidationError.builder().field(ex.getBindingResult().getFieldError().getField()).message(ex.getBindingResult().getFieldError().getDefaultMessage()).build());
        return ValidationErrors.builder().errors(errors).build();
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrors handleMailException(MailException ex) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(ValidationError.builder().field("email").message(ex.getLocalizedMessage()).build());
        return ValidationErrors.builder().errors(errors).build();
    }

    @ExceptionHandler(SessionTerminatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrors handleMailException(SessionTerminatedException ex) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(ValidationError.builder().field("session").message(ex.getLocalizedMessage()).build());
        return ValidationErrors.builder().errors(errors).build();
    }

}
