package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Amount {

    @Id
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "amountGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "AMOUNT_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private BigDecimal value;
    private String unit;
    private String currency;

}
