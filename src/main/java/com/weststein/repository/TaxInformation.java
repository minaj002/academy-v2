package com.weststein.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxInformation {

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
