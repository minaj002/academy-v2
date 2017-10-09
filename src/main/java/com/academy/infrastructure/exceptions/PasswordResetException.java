package com.academy.infrastructure.exceptions;

public class PasswordResetException extends RuntimeException {

    public PasswordResetException(String message) {
        super(message);
    }
}
