package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class BusinessInformation {

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
    private String enterpriseName;
    private String legalStatus;
    @OneToOne(cascade= CascadeType.ALL)
    private Address address;
    private LocalDate dateOfIncorporation;
    private String registrationNumber;
    private Boolean agree;
    private LocalDateTime agreeOn;
    @Enumerated(EnumType.STRING)
    private Language language;

}