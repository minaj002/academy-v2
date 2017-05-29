package com.weststein.controller.secured.model;

import com.weststein.repository.Identification;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Identifications {

    private List<Identification> identifications;

}
