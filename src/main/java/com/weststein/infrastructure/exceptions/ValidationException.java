package com.weststein.infrastructure.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException {

    private List<ValidationError> errors = new ArrayList<>();

    public ValidationException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
