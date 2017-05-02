package com.weststein.integration;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SolarisAmount {

    private BigDecimal value;
    private String unit;
    private String currency;

}
