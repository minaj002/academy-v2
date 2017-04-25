package com.weststein.controller.secured.model;

import com.weststein.integration.Person;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Persons {

    private List<Person> persons;

}
