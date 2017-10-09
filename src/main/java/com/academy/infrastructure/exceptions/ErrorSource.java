package com.academy.infrastructure.exceptions;

import lombok.Data;

@Data
public class ErrorSource {

    private String field;
    private String message;

}
