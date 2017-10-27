package com.academy.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Member {

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
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String phone;
    private String email;
    private String academyName;
    private Date joinDate;
    @ManyToMany
    private List<Section> sections = Collections.emptyList();
    @OneToMany
    private List<Payment> payments = Collections.emptyList();
    private Long initialClassCount;
}
