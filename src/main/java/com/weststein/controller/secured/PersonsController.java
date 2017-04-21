package com.weststein.controller.secured;

import com.weststein.controller.model.Applications;
import com.weststein.handler.GetPersonsHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonsController {

    @Autowired
    private GetPersonsHandler getPersonsHandler;

    @GetMapping
    @ApiOperation(value = "see all Persons", response = Applications.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersons(){

        return new ArrayList();
    }

}
