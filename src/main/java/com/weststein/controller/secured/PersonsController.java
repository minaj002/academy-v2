package com.weststein.controller.secured;

import com.weststein.controller.secured.model.Persons;
import com.weststein.handler.person.CreatePersonHandler;
import com.weststein.handler.person.GetPersonHandler;
import com.weststein.handler.person.GetPersonsHandler;
import com.weststein.handler.person.UpdatePersonHandler;
import com.weststein.integration.SolarisPerson;
import com.weststein.repository.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/persons")
public class PersonsController {

    @Autowired
    private GetPersonsHandler getPersonsHandler;
    @Autowired
    private GetPersonHandler getPersonHandler;
    @Autowired
    private CreatePersonHandler createPersonHandler;
    @Autowired
    private UpdatePersonHandler updatePersonHandler;

    @GetMapping
    @ApiOperation(value = "see all Persons", response = Persons.class)
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
    public Person getPerson(@PathVariable String personId) {
        return getPersonHandler.handle(personId);
    }

    @PostMapping
    @ApiOperation(value = "create new Person", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "")
    })
    public Person createPerson(@RequestBody Person person){
        return createPersonHandler.handle(person);
    }

    @PatchMapping
    @ApiOperation(value = "update Person info", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Person updatePerson(@RequestBody Person person){
        return updatePersonHandler.handle(person);
    }
}
