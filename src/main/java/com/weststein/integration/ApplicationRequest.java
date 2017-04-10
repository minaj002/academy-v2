package com.weststein.integration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationRequest {

    private String userName;
    private String password;
    private CardIssue data;

}
