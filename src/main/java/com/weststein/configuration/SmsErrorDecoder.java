package com.weststein.configuration;

import com.weststein.infrastructure.exceptions.SmsError;
import com.weststein.infrastructure.exceptions.Errors;
import com.weststein.infrastructure.exceptions.ValidationError;
import com.weststein.infrastructure.exceptions.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmsErrorDecoder implements ErrorDecoder {

    @Override
    public ValidationException decode(String methodKey, Response response) {

        JacksonDecoder jacksonDecoder = new JacksonDecoder();
        List<ValidationError> validationErrors = new ArrayList<>();
        try {
            SmsError error = (SmsError) jacksonDecoder.decode(response, Errors.class);
            validationErrors.add(ValidationError.builder().field(error.getError()).message(error.getError()).build());
            return new ValidationException(validationErrors, "error from sms");
        } catch (IOException e) {
            validationErrors.add(ValidationError.builder().message(e.getMessage()).build());
            return new ValidationException(validationErrors, "error from sms");
        }
    }
}
