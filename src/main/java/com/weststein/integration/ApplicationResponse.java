package com.weststein.integration;

import com.weststein.integration.response.CardIssueResponse;
import lombok.Data;

@Data
public class ApplicationResponse {

    private String errorCode;
    private String description;
    private CardIssueResponse cardIssue;
}
