package com.weststein.controller.secured;

import com.weststein.controller.secured.model.Accounts;
import com.weststein.controller.secured.model.Bookings;
import com.weststein.handler.account.GetPersonAccountBookingsHandler;
import com.weststein.handler.account.GetPersonAccountHandler;
import com.weststein.handler.account.GetPersonAccountsHandler;
import com.weststein.repository.Account;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/persons")
public class AccountsController {

    @Autowired
    private GetPersonAccountsHandler getPersonAccountsHandler;
    @Autowired
    private GetPersonAccountHandler getPersonAccountHandler;
    @Autowired
    private GetPersonAccountBookingsHandler getPersonAccountBookingsHandler;

    @GetMapping("/{personId}/accounts")
    @ApiOperation(value = "see all accounts for this person", response = Accounts.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Accounts getPersonAccounts(@PathVariable String personId) {
        return Accounts.builder().accounts(getPersonAccountsHandler.handle(personId)).build();
    }

    @GetMapping("/{personId}/accounts/{accountId}")
    @ApiOperation(value = "see account for this person", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Account getPersonAccount(@PathVariable String personId, @PathVariable String accountId) {
        return getPersonAccountHandler.handle(accountId);
    }

    @GetMapping("/{personId}/accounts/{accountId}/bookings")
    @ApiOperation(value = "see bookings for this persons account", response = Bookings.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Bookings getPersonAccountBookings(@PathVariable String personId, @PathVariable String accountId) {
        return Bookings.builder().bookings(getPersonAccountBookingsHandler.handle(accountId)).build();
    }

}
