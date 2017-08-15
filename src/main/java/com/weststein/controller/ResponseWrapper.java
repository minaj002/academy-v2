package com.weststein.controller;

import com.weststein.infrastructure.exceptions.ValidationError;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ResponseWrapper <T>{

    private List<String> messages= new ArrayList<>();
    private List<ValidationError> errors = new ArrayList<>();
    private T response;

}
