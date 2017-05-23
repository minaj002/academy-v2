package com.weststein.repository;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Document {

    @Id
    @GenericGenerator(strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            name = "documentGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "DOCUMENT_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String solarisId;
    private String name;
    private String contentType;
    private String documentType;
    private String size;
    private String customerAccessible;
    private Date deletedAt;

}
