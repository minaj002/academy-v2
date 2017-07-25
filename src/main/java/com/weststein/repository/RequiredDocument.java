package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class RequiredDocument {

    public enum Type {
        CertificateOfRegistration, ArticleOfAssociation,
        AnnualReturn, ConfirmationOfShareholdingDetails,
        ConfirmationOfCompanyDirectors, BankAccountEvidence,
        ShareholderPhotoId, ShareholderAddressVerification,
        DirectorPhotoId, DirectorAddressVerification
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
    private LocalDateTime created;
    private Type type;
    private String bucket;
    private String name;
}
