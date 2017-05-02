package com.weststein.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person {

    public enum Salutation {
        MR, MS
    }

    public enum EmploymentStatus {
        EMPLOYED,
        UNEMPLOYED,
        PUBLIC_SECTOR_EMPLOYEE,
        PROFESSIONAL_SOLDIER,
        FREELANCER,
        HOUSEWORK,
        APPRENTICE,
        MANAGEMENT,
        RETIRED,
        STUDENT,
        SELF_EMPLOYED,
        MILITARY_OR_COMMUNITY_SERVICE
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    private boolean dirty;
    @Column(unique = true)
    private String solarisId;
    private Salutation salutation;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String birthDate;
    private String birthCountry;
    private String birthCity;
    private String nationality;
    private EmploymentStatus employmentStatus;
    private String jobTitle;
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    @OneToOne(cascade=CascadeType.ALL)
    private Address contactAddress;
    @OneToOne(cascade=CascadeType.ALL)
    private TaxInformation taxInformation;
}
