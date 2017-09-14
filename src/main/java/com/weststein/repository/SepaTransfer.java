package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class SepaTransfer {

    public enum Status {
       DRAFT, SIGNING, COMPLETED, REJECTED, DECLINED, DELETED
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
    private String beneficiary;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    @Column(columnDefinition = "TEXT")
    private String details;
    private String iban;
    private String bic;
    @OneToOne
    private CardholderId from;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime created;
    private BigDecimal predictedFee;
    private String referenceId;
    @OneToOne
    private Currency currency;
}
