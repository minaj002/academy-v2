package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Account {

    private String id;
    private String iban;
    private String bic;
    private String type;
    private Balance balance;
    @JsonProperty("locking-status")
    private String lockingStatus;
    @JsonProperty("person_id")
    private String personId;
    @JsonProperty("business_id")
    private String businessId;

}
