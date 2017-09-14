package com.weststein.repository.business;

import com.weststein.pdf.CurrencyEnum;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class BankAccountDetails {

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
    private Long businessId;
    private LocalDateTime created;
    private CurrencyEnum currency;
    private String bic;
    private String iban;
    private String sortCode;
    private String accountNumber;
    private String routingNumber;
    private String bankNameAndAddress;
    private String accountHolderName;

}
