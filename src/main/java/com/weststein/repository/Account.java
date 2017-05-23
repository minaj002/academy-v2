package com.weststein.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String solarisId;
    private String iban;
    private String bic;
    @Enumerated(EnumType.STRING)
    private Type type;
    @OneToOne(cascade=CascadeType.ALL)
    private Amount balance;
    private String lockingStatus;
    private String personId;
    private String businessId;

    public enum Type {
        GENERAL_LEDGER,
        COLLECTION_ACCOUNT,
        CLEARING_ACCOUNT,
        CHECKING_BUSINESS,
        CHECKING_PERSONAL,
        CHECKING_OVERDRAFT,
        ESCROW_LIABILITY,
        CHECKING_FIXED_MATURITY,
        LOAN_RATE_VARIABLE,
        LOAN_RATE_FIXED,
        LOAN_BALLOON,
        LOAN_BULLET,
        CASH_DEPOSIT,
        DEPOSIT_TIME_PASSIVE,
        BILLING_ACCOUNT,
        SETTLEMENT
    }

}
