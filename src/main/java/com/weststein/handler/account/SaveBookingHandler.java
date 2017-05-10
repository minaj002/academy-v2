package com.weststein.handler.account;

import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.SolarisBooking;
import com.weststein.repository.Booking;
import com.weststein.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveBookingHandler {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(SolarisBooking booking) {
        bookingRepository.save(objectMapper.map(booking, Booking.class));
    }


}
