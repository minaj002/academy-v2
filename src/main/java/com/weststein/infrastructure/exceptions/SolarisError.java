package com.weststein.infrastructure.exceptions;

import lombok.Data;

@Data
public class SolarisError {

    private String code;
    private ErrorSource source;

}
