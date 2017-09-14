package com.weststein.repository.business;

import com.weststein.pdf.CurrencyEnum;
import com.weststein.repository.CardholderCategory;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class CardIban {

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
    private String explanationOfPurpose;
    @Enumerated(EnumType.STRING)
    private CardholderCategory holders;
    private String otherHoldersExplanation;
    private String holdersLocation;
    @Enumerated(EnumType.STRING)
    private CardUse use;
    private String otherCardUse;
    private CurrencyEnum currency;
    private Integer numberOfCards;
    @Enumerated(EnumType.STRING)
    private CardType type;
    private String natureOfExpectedTransactions;
    private String fundsOrigin;
    private String flowOfFunds;
    private LocalDateTime created;

}
