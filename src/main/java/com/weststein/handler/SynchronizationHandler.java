package com.weststein.handler;

import com.weststein.controller.secured.model.FullModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.integration.*;
import com.weststein.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SynchronizationHandler {

    @Autowired
    private PersonResource personResource;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AccountResource accountResource;
    @Autowired
    private OrikoObjectMapper objectMapper;

    public FullModel handle() {
        List<SolarisPerson> solarisPersons = personResource.getAll();
        List<String> solarisPersonIds = solarisPersons.stream().map(person -> person.getSolarisId()).collect(Collectors.toList());
        List<Person> savedPersons = personRepository.findAllBySolarisIdIn(solarisPersonIds);
        FullModel model = new FullModel();
        if(savedPersons.size() != solarisPersonIds.size()) {
            List<String> savedPersonsIds = savedPersons.stream().map(person -> person.getSolarisId()).collect(Collectors.toList());
            List<SolarisPerson> solarisPersonsNotInDb = solarisPersons.stream().filter(person -> !savedPersonsIds.contains(person.getSolarisId())).collect(Collectors.toList());

            Iterable<Person> insertedPersons = personRepository.save(objectMapper.map(solarisPersonsNotInDb, Person.class));
            model.setPersons(StreamUtils.createStreamFromIterator(insertedPersons.iterator()).collect(Collectors.toList()));
        }
        List<SolarisAccount> solarisAccounts = new ArrayList<>();
        for (String id : solarisPersonIds) {
            solarisAccounts.addAll(accountResource.getAccounts(id));
        }
        List<String> solarisAccountIds = solarisAccounts.stream().map(account -> account.getSolarisId()).collect(Collectors.toList());

        List<Account> accountsInDB = accountRepository.findAllBySolarisIdIn(solarisAccountIds);
        if(accountsInDB.size() != solarisAccounts.size()) {
            List<String> savedAccountIds = accountsInDB.stream().map(account -> account.getSolarisId()).collect(Collectors.toList());
            List<SolarisAccount> solarisAccountsNotInDB = solarisAccounts.stream().filter(account -> !savedAccountIds.contains(account.getSolarisId())).collect(Collectors.toList());

            Iterable<Account> savedAccounts = accountRepository.save(objectMapper.map(solarisAccountsNotInDB, Account.class));
            model.setAccounts(StreamUtils.createStreamFromIterator(savedAccounts.iterator()).collect(Collectors.toList()));
        }

        List<SolarisBooking> solarisBookings = new ArrayList<>();
        for(SolarisAccount account : solarisAccounts) {
            solarisBookings.addAll(accountResource.getAccountBookings(account.getPersonId(), account.getSolarisId())
                    .stream()
                    .map(solarisBooking -> { solarisBooking.setAccountId(account.getSolarisId()); return solarisBooking;})
                    .collect(Collectors.toList()));
        }
        List<String> solarisBookingIds = solarisBookings.stream().map(booking -> booking.getSolarisId()).collect(Collectors.toList());

        List<Booking> bookingsInDB = bookingRepository.findAllBySolarisIdIn(solarisBookingIds);
        if(accountsInDB.size() != solarisAccounts.size()) {
            List<String> savedBookingIds = bookingsInDB.stream().map(account -> account.getSolarisId()).collect(Collectors.toList());
            List<SolarisBooking> solarisBookingsNotInDB = solarisBookings.stream().filter(booking -> !savedBookingIds.contains(booking.getSolarisId())).collect(Collectors.toList());

            Iterable<Booking> savedBookings = bookingRepository.save(objectMapper.map(solarisBookingsNotInDB, Booking.class));
            model.setBookings(StreamUtils.createStreamFromIterator(savedBookings.iterator()).collect(Collectors.toList()));
        }


        return model;
    }

}
