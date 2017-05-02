package com.weststein.handler.account;

import com.weststein.integration.SolarisAccount;
import com.weststein.integration.AccountResource;
import com.weststein.repository.Account;
import com.weststein.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountHandler {

    @Autowired
    private AccountRepository accountRepository;

    public Account handle(String accountId) {
        return accountRepository.findBySolarisId(accountId);
    }


}
