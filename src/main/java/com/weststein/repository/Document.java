package com.weststein.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String solarisId;
    private String name;
    private String contentType;
    private String documentType;
    private String size;
    private String customerAccessible;
    private Date deletedAt;

}
