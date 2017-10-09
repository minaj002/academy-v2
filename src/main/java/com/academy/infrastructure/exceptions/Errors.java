package com.academy.infrastructure.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class Errors {

    private List<SmsError> errors;

}
