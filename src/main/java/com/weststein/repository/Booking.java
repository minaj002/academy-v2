package com.weststein.repository;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String solarisId;
    private Date creationDate;
    private Date valutaDate;
    private Date bookingDate;
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
