package com.weststein.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxInformation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
