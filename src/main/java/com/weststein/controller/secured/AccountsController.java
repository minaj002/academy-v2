package com.weststein.controller.secured;

import com.weststein.handler.*;
import com.weststein.integration.Account;
import com.weststein.integration.SolarisPerson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountsController {

    @Autowired
    private GetPersonAccountsHandler getPersonAccountsHandler;
    @Autowired
    private GetPersonAccountHandler getPersonAccountHandler;
    @Autowired
    private GetPersonAccountBookingsHandler getPersonAccountBookingsHandler;

    @GetMapping("/{personId}")
    @ApiOperation(value = "see all accounts for this person", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonAccounts(String personId){
        return getPersonAccountsHandler.handle(personId);
    }


    @GetMapping("/{personId}/{accountId}")
    @ApiOperation(value = "see account for this person", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonAccounts(String personId, String accountId){
        return getPersonAccountHandler.handle(personId, accountId);
    }

    @GetMapping("/{personId}/{accountId}/bookings")
    @ApiOperation(value = "see bookings for this persons account", response = SolarisPerson.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonAccountBookings(String personId, String accountId){
        return getPersonAccountBookingsHandler.handle(personId, accountId);
    }



}
