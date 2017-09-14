package com.weststein.controller.secured.model.business;

import com.weststein.pdf.CurrencyEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectedLoadingFiguresModel {

    private CurrencyEnum currency;
    private BigDecimal monthly;
    private BigDecimal quarterly;
    private BigDecimal yearly;

}
