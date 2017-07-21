package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class CompanyInformation {

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
    private Long businessId;
    private String legalName;
    private String tradingName;
    private String legalStatus;
    @OneToOne(cascade= CascadeType.ALL)
    private Address registeredAddress;
    private String principalPlaceOfBusiness;
    private String landLinePhone;
    private String mobilePhone;
    private Title contactTitle;
    private String firstName;
    private String lastName;
    private String contactEmail;
    private String contactPhone;
    private String dateOfIncorporation;
    private String registrationNumber;
    private String taxNumber;
    private String vatNumber;
    private boolean regulatedByAuthority;
    private String regulationAuthorityName;
    private String licenceNumberGrantedByAuthority;
    private LocalDateTime created;


}
