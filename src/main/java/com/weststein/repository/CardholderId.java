package com.weststein.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class CardholderId {

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
    private String cardholderId;
    private String iban;

}
