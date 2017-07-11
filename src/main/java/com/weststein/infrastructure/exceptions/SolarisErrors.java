package com.weststein.infrastructure.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class SolarisErrors {

    private List<SmsError> errors;

}
