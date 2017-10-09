package com.academy.infrastructure.exceptions;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }
}
