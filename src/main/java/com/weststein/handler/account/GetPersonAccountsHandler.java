package com.weststein.handler.account;

import com.weststein.repository.Account;
import com.weststein.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountsHandler {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> handle(String personId) {
        return accountRepository.findAllByPersonId(personId);
    }


}
