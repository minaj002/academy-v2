package com.weststein.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Address {

    @Id
    private Long id;
    private String line1;
    private String line2;
    private String postalCode;
    private String city;
    private String country;
    private String state;

}
