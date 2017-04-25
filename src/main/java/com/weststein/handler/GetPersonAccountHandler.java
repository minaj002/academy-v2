package com.weststein.handler;

import com.weststein.integration.Account;
import com.weststein.integration.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountHandler {

    @Autowired
    private AccountResource accountResource;

    public List<Account> handle(String personId, String accountId) {

        return accountResource.getAccount(personId, accountId);

    }


}
