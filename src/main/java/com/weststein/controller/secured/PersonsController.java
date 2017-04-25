package com.weststein.controller.secured;

import com.weststein.controller.secured.model.Persons;
import com.weststein.handler.GetPersonAccountsHandler;
import com.weststein.handler.GetPersonHandler;
import com.weststein.handler.GetPersonsHandler;
import com.weststein.integration.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonsController {

    @Autowired
    private GetPersonsHandler getPersonsHandler;
    @Autowired
    private GetPersonHandler getPersonHandler;

    @Autowired
    private GetPersonAccountsHandler getPersonAccountsHandler;

    @GetMapping
    @ApiOperation(value = "see all Persons", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Persons getPersons(){
        return Persons.builder().persons(getPersonsHandler.handle()).build();
    }

    @GetMapping("/{personId}")
    @ApiOperation(value = "see Person info by id", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Person getPerson(String personId){
        return getPersonHandler.handle(personId);
    }

    @GetMapping("/{personId}/accounts")
    @ApiOperation(value = "see all accounts for this person", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonAccounts(String personId){
        return getPersonAccountsHandler.handle(personId);
    }



}
