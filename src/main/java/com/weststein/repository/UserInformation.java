package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Audited
public class UserInformation {

    public enum Gender {
        MALE, FEMALE
    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private LocalDate dateOfBirth;
    private Boolean phoneVerified;
    private String phoneVerificationCode;
    @OneToOne(cascade= CascadeType.ALL)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean agree;
    private LocalDateTime agreeOn;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<CardholderId> cardholderIds;


}