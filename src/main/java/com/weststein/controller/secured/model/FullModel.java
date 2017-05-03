package com.weststein.controller.secured.model;

import com.weststein.repository.Account;
import com.weststein.repository.Booking;
import com.weststein.repository.Identification;
import com.weststein.repository.Person;
import lombok.Data;

import java.util.List;

@Data
public class FullModel {

    private List<Person> persons;
    private List<Account> accounts;
    private List<Booking> bookings;
    private List<Identification> identifications;

}
