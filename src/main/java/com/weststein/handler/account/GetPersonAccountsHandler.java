package com.weststein.handler.account;

import com.weststein.integration.SolarisAccount;
import com.weststein.integration.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountsHandler {

    @Autowired
    private AccountResource accountResource;

    public List<SolarisAccount> handle(String personId) {

        return accountResource.getAccounts(personId);

    }


}
