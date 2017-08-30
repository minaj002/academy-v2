package com.weststein.controller.secured.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionModel {

    private LocalDateTime date;
    private String transactionType;
    private String beneficiary;
    private String details;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String status;

}
