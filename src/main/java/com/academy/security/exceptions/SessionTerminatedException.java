package com.academy.security.exceptions;

public class SessionTerminatedException extends RuntimeException {

    public SessionTerminatedException(String message) {
        super(message);
    }

}
