package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SolarisIdentification {

    @JsonProperty("id")
    private String solarisId;
    private String reference;
    private String url;
    private String status;
    @JsonProperty("completed_at")
    private Date completedAt;
    private String method;
    private String address;
    private String personId;
    //    @JsonDeserialize(contentAs = SolarisDocument.class)
    private List<SolarisDocument> documents = new ArrayList<>();


}
