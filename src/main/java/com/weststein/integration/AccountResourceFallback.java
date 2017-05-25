package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountResourceFallback implements AccountResource {


    @Override
    public List<SolarisAccount> getAccounts(String personId) {
        return null;
    }

    @Override
    public SolarisAccount getAccount(String personId, String accountId) {
        return null;
    }

    public List<SolarisBooking> getAccountBookings(String personId, String accountId) {
        return null;
    }

}
