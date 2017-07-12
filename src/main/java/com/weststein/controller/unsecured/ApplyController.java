package com.weststein.controller.unsecured;

import com.weststein.controller.unsecured.model.ApplicationModel;
import com.weststein.handler.application.ApplicationHandler;
import com.weststein.validator.EmailValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.AbstractErrors;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
public class ApplyController {

    @Autowired
    private ApplicationHandler applicationHandler;
    @Autowired
    private EmailValidator emailValidator;

    @PostMapping("/api/apply")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public ResponseEntity login(@Valid @RequestBody @DateTimeFormat(pattern = "ddMMyyyy") ApplicationModel application){
        applicationHandler.handle(application);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/apply/verify")
    @ApiOperation(value = "allow new user to apply for new membership")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity validateEmail(@Valid @Email @RequestParam String email){

        emailValidator.validate(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
