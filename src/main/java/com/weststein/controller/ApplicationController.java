package com.weststein.controller;

import com.weststein.controller.model.ApplicationForm;
import com.weststein.controller.model.ApplicationResponse;
import com.weststein.handler.ApplicationHandler;
import com.weststein.handler.GetApplicationHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("application")
public class ApplicationController {

   @Autowired
   private ApplicationHandler applicationHandler;
   @Autowired
   private GetApplicationHandler getApplicationHandler;


   @PostMapping("apply")
   @ApiOperation(value = "allow new user apply for WestStein services", response = ApplicationResponse.class)
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "")
   })
   public ApplicationResponse apply(@Valid @RequestBody @DateTimeFormat(pattern = "ddMMyyyy") ApplicationForm application) {
        ApplicationResponse response = applicationHandler.handle(application);
        return response;
   }

    @GetMapping
    @ApiOperation(value = "see all applications to WestStein services", response = ApplicationForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List<ApplicationForm> applications() {
        return getApplicationHandler.handle();
    }



}
