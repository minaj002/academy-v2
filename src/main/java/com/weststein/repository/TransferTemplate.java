package com.weststein.repository;

import com.weststein.repository.business.BusinessInformation;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class TransferTemplate {

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
    private String details;
    private String iban;
    private String bic;
    private LocalDateTime created;
    @OneToOne
    private BusinessInformation business;
    private String name;
    @OneToOne
    private Currency currency;
}
