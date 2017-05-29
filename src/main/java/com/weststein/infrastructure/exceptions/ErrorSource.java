package com.weststein.infrastructure.exceptions;

import lombok.Data;

@Data
public class ErrorSource {

    private String field;
    private String message;

}
