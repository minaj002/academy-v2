package com.academy.infrastructure.exceptions;

public class AccountVerificationException extends RuntimeException {

    public AccountVerificationException(String message) {
        super(message);
    }
}
