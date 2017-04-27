package com.weststein.repository;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private boolean dirty;
    private String solarisId;
    private String salutation;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String birthDate;
    private String birthCountry;
    private String birthCity;
    private String nationality;
    private String employmentStatus;
    private String jobTitle;
    @OneToOne
    private Address address;
    @OneToOne
    private Address contactAddress;
    @OneToOne
    private TaxInformation taxInformation;
}
