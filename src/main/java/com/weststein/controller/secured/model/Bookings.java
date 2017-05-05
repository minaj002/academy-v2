package com.weststein.controller.secured.model;

import com.weststein.repository.Booking;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Bookings {

    private List<Booking> bookings;

}
