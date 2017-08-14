package com.weststein.controller.secured;

import com.weststein.controller.secured.model.business.AuthorizedUsers;
import com.weststein.handler.business.GetAuthorizedUsersHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @Autowired
    private GetAuthorizedUsersHandler getAuthorizedUsersHandler;

    @GetMapping(value = "/api/business/{businessId}/authorized-users")
    @ApiOperation(value = "get authorized users for this business")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public AuthorizedUsers getAuthorizedUsers(@PathVariable Long businessId) {
        return AuthorizedUsers.builder().authorizedUsers(getAuthorizedUsersHandler.handle(businessId)).build();
    }

}