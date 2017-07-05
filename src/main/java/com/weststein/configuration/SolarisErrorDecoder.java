package com.weststein.configuration;

import com.weststein.infrastructure.exceptions.SolarisErrors;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolarisErrorDecoder implements ErrorDecoder {

    @Override
    public ValidationException decode(String methodKey, Response response) {

        JacksonDecoder jacksonDecoder = new JacksonDecoder();
        List<ValidationError> validationErrors = new ArrayList<>();
        try {
            SolarisErrors errors = (SolarisErrors) jacksonDecoder.decode(response, SolarisErrors.class);
            errors.getErrors().forEach(e -> validationErrors.add(ValidationError.builder().field(e.getSource().getField()).message(e.getSource().getMessage()).build()));
            return new ValidationException(validationErrors, "error from pfs");
        } catch (IOException e) {
            validationErrors.add(ValidationError.builder().message(e.getMessage()).build());
            return new ValidationException(validationErrors, "error from pfs");
        }
    }

}
