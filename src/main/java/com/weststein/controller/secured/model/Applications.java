package com.weststein.controller.secured.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Applications {

    private List<ApplicationForm> applications;

}
