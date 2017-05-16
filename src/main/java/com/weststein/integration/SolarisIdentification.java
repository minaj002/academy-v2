package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weststein.configuration.CustomDeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SolarisIdentification {

    @JsonProperty("id")
    private String solarisId;
    private String reference;
    private String url;
    private String status;
    @JsonProperty("completed_at")
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss Z")
    @JsonDeserialize(using = CustomDeSerializer.class)
    private LocalDateTime completedAt;
    private String method;
    private String address;
    private String personId;
    private List<SolarisDocument> documents = new ArrayList<>();

}
