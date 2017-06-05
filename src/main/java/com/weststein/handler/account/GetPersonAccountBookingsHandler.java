package com.weststein.handler.account;

import com.weststein.repository.Booking;
import com.weststein.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPersonAccountBookingsHandler {

    @Autowired
    private BookingRepository bookingRepository;

    public Page<Booking> handle(String accountId, Pageable pageable) {
        return bookingRepository.findByAccountId(accountId, pageable);
    }


}
