package com.weststein.controller.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Applications {

    private List<ApplicationForm> applications;

}
