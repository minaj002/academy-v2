package com.weststein.integration;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {

    private BigDecimal value;
    private String unit;
    private String currency;

}
