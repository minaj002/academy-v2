package com.weststein.infrastructure.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrors {

    private List<ValidationError> errors;

}
