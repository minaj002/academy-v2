package com.weststein.controller.secured.model;

import com.weststein.repository.Person;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Persons {

    private List<Person> persons;
    private int total;

}
