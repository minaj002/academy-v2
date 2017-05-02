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
    private String type;
    @OneToOne(cascade=CascadeType.ALL)
    private Amount balance;
    private String lockingStatus;
    private String personId;
    private String businessId;

}
