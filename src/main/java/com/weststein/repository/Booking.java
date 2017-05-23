package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Booking {

    @Id
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "bookingGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "BOOKING_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String solarisId;
    private LocalDate creationDate;
    private LocalDate valutaDate;
    private LocalDate bookingDate;
    @Enumerated(EnumType.STRING)
    private BookingType bookingType;
    @OneToOne(cascade=CascadeType.ALL)
    private Amount amount;
    private String description;
    private String recipientBic;
    private String recipientIban;
    private String recipientName;
    private String senderBic;
    private String senderIban;
    private String senderName;
    private String endToEndId;
    private String creditorIdentifier;
    private String mandateReference;
    private String transactionId;
    private String accountId;

    public enum BookingType {
        SEPA_CREDIT_TRANSFER,
        DIRECT_DEBIT,
        ACCOUNT_MAINTENANCE_CHARGES,
        OTHER
    }

}
