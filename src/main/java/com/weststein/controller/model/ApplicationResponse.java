package com.weststein.controller.model;

import com.weststein.integration.CardIssue;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {

    private CardIssue data;

}
