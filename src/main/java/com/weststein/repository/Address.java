package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Audited
public class Address {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @NotEmpty
    private String line1;
    private String line2;
    @NotEmpty
    private String postalCode;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;

    @Override
    public String toString() {
        return line1 + ", " + line2 + ", " + city + ", " + country +", " +postalCode;
    }
}
