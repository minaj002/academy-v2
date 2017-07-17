package com.weststein.controller.secured.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CardholderIdsModel {

    private Set<String> cardholderIds;

}
