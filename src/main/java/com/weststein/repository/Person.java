package com.weststein.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @JsonIgnore
    private boolean dirty;
    @Column(unique = true)
    private String solarisId;
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    private String jobTitle;
    @OneToOne(cascade=CascadeType.ALL)
    private Address address;
    @OneToOne(cascade=CascadeType.ALL)
    private Address contactAddress;

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
}
