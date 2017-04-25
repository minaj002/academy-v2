package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountResourceFallback implements AccountResource {


    @Override
    public List<Account> getAccounts(String personId) {
        return null;
    }

    @Override
    public List<Account> getAccount(String personId, String accountId) {
        return null;
    }
}
