package com.weststein.controller.secured.model;

import com.weststein.repository.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferTemplateModel {

    private Long id;
    private String beneficiary;
    private BigDecimal amount;
    private String details;
    private String iban;
    private String bic;
    private LocalDateTime created;
    private String name;
    private Currency currency;

}
