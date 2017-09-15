package com.weststein.infrastructure.exceptions;

import com.weststein.controller.ResponseWrapper;
import com.weststein.security.exceptions.SessionTerminatedException;
import org.iban4j.*;
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

    @ExceptionHandler(Iban4jException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseWrapper handleIban4jException(Iban4jException ex) {
        List<ValidationError> errors = new ArrayList<>();
        if (ex instanceof BicFormatException) {
            errors.add(ValidationError.builder().field("bic").message(ex.getMessage()).build());
        } else if (ex instanceof IbanFormatException || ex instanceof InvalidCheckDigitException) {
            errors.add(ValidationError.builder().field("iban").message(ex.getMessage()).build());
        } else {
            UnsupportedCountryException e = (UnsupportedCountryException) ex;
            errors.add(ValidationError.builder().field("iban").message(e.getMessage() + " " + e.getCountryCode()).build());
            errors.add(ValidationError.builder().field("bic").message(e.getMessage() + " " + e.getCountryCode()).build());
        }
        return ResponseWrapper.builder().errors(errors).build();
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseWrapper handleMailException(MailException ex) {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(ValidationError.builder().field("email").message(ex.getLocalizedMessage()).build());
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
