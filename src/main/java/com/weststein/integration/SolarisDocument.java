package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SolarisDocument {

    @JsonProperty("id")
    private String solarisId;
    private String name;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("document_type")
    private String documentType;
    private String size;
    @JsonProperty("customer_accessible")
    private String customerAccessible;
    @JsonProperty("deleted_at")
    private Date deletedAt;

}
