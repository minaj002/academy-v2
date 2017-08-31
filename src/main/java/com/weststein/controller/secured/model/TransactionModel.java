package com.weststein.controller.secured.model;

import com.weststein.handler.viewstatement.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionModel {

    private LocalDateTime date;
    private TransactionType transactionType;
    private String beneficiary;
    private String details;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String status;

}
