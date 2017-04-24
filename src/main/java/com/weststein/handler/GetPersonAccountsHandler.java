package com.weststein.handler;

import com.weststein.integration.Account;
import com.weststein.integration.PersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountsHandler {

    @Autowired
    private PersonResource personResource;

    public List<Account> handle(String personId) {

        return personResource.getAccounts(personId);

    }


}
