package com.weststein.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxInformation {

    @Id
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "taxGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TAX_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private TaxAssessment taxAssessment;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    public enum TaxAssessment {
        NONE, SEPARATE, JOINT
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, UNKNOWN
    }

}
