package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class UserInformation {

    public enum Gender {
        MALE, FEMALE
    }
    public enum Language {
        LV, RU, EN
    }

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
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String phone;
    private Boolean phoneVerified;
    private String phoneVerificationCode;
    @OneToOne(cascade= CascadeType.ALL)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean agree;
    private LocalDateTime agreeOn;
    @Enumerated(EnumType.STRING)
    private Language language;

}