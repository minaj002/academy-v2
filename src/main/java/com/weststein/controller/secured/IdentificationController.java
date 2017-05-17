package com.weststein.controller.secured;

import com.weststein.handler.identification.GetPersonIdentificationHandler;
import com.weststein.handler.identification.GetPersonIdentificationsHandler;
import com.weststein.repository.Identification;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class IdentificationController {

    @Autowired
    private GetPersonIdentificationsHandler getPersonIdentificationsHandler;
    @Autowired
    private GetPersonIdentificationHandler getPersonIdentificationHandler;

    @GetMapping("/{personId}/identification")
    @ApiOperation(value = "see all identifications for this person", response = Identification.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonIdentifications(@PathVariable String personId) {
        return getPersonIdentificationsHandler.handle(personId);
    }

    @GetMapping("/{personId}/identification/{id}")
    @ApiOperation(value = "see identification", response = Identification.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Identification getPersonIdentification(@PathVariable String id) {
        return getPersonIdentificationHandler.handle(id);
    }

}
