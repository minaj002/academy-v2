package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Identification {

    @Id
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "identificationGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "IDENTIFICATION_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String solarisId;
    private String reference;
    private String url;
    private String status;
    private LocalDateTime completedAt;
    private String method;
    private String address;
    private String personId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> documents;

}
