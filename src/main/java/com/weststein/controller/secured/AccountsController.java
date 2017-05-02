package com.weststein.controller.secured;

import com.weststein.handler.account.GetPersonAccountBookingsHandler;
import com.weststein.handler.account.GetPersonAccountHandler;
import com.weststein.handler.account.GetPersonAccountsHandler;
import com.weststein.integration.SolarisAccount;
import com.weststein.integration.SolarisPerson;
import com.weststein.repository.Account;
import com.weststein.repository.Booking;
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
    @ApiOperation(value = "see all accounts for this person", response = SolarisAccount.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonAccounts(String personId){
        return getPersonAccountsHandler.handle(personId);
    }

    @GetMapping("/{accountId}")
    @ApiOperation(value = "see account for this person", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Account getPersonAccount(String accountId){
        return getPersonAccountHandler.handle(accountId);
    }

    @GetMapping("/{accountId}/bookings")
    @ApiOperation(value = "see bookings for this persons account", response = Booking.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public List getPersonAccountBookings(String accountId){
        return getPersonAccountBookingsHandler.handle(accountId);
    }

}
