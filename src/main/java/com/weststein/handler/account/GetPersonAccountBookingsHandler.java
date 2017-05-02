package com.weststein.handler.account;

import com.weststein.integration.SolarisAccount;
import com.weststein.integration.AccountResource;
import com.weststein.repository.Booking;
import com.weststein.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountBookingsHandler {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> handle(String accountId) {

        return bookingRepository.findByAccountId(accountId);

    }


}
