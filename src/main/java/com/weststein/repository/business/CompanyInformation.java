package com.weststein.repository.business;

import com.weststein.repository.Address;
import com.weststein.repository.Title;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class CompanyInformation {

    public enum LegalStatus {

        Private("Private Limited Company"), Public("Public Limited Company"), Other("Other");

        private final String description;

        LegalStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
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
    private Long businessId;
    private String legalName;
    private String tradingName;
    @Enumerated(EnumType.STRING)
    private LegalStatus legalStatus;
    private String otherLegalStatus;
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
    private Boolean regulatedByAuthority;
    private String regulationAuthorityName;
    private String licenceNumberGrantedByAuthority;
    private LocalDateTime created;


}
