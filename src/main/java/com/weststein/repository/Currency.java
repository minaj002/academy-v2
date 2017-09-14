package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by artis on 08/09/2017.
 */

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String code;
    private String name;
    private Boolean convertOnline;
    private Integer decimalPlaces;

}
