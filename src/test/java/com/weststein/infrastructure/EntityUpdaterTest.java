package com.weststein.infrastructure;

import com.weststein.repository.Address;
import com.weststein.repository.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityUpdaterTest {
    @Test
    public void apply() {

        EntityUpdater updater = new EntityUpdater();
        Person inPerson = new Person();
        inPerson.setEmail("mail@mail.com");
        Address address = new Address();
        address.setCountry("USA");
        inPerson.setAddress(address);
        Person outPerson = new Person();
        Address outAddress = new Address();
        outAddress.setCity("New York");
        outPerson.setAddress(outAddress);
        inPerson.setSalutation(Person.Salutation.MR);

        updater.apply(inPerson, outPerson);

        assertEquals("mail@mail.com", outPerson.getEmail());
        assertEquals("USA", outPerson.getAddress().getCountry());
        assertEquals("New York", outPerson.getAddress().getCity());
        assertEquals(Person.Salutation.MR, outPerson.getSalutation());
    }

}