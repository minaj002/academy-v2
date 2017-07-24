package com.weststein.controller.secured.model.business;

import com.weststein.repository.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectedLoadingFiguresModel {

    private Currency currency;
    private BigDecimal monthly;
    private BigDecimal quarterly;
    private BigDecimal yearly;

}
