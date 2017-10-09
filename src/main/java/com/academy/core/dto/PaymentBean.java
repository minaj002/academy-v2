package com.academy.core.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentBean {

    private Long id;
    private BigDecimal amount;
    private Date paymentDate;
    private Date paidUntil;
    private Long memberId;

}
