package com.weststein.repository;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
