package com.weststein.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weststein.repository.Account;
import lombok.Data;

@Data
public class SolarisAccount {

    @JsonProperty("id")
    private String solarisId;
    private String iban;
    private String bic;
    private Account.Type type;
    private SolarisAmount balance;
    @JsonProperty("locking-status")
    private String lockingStatus;
    @JsonProperty("person_id")
    private String personId;
    @JsonProperty("business_id")
    private String businessId;

}
