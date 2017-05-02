package com.weststein.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Amount {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private BigDecimal value;
    private String unit;
    private String currency;

}
