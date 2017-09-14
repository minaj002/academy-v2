package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeCardStatus {

    @JacksonXmlProperty(localName = "CardHolderID")
    private String cardHolderId;
    @JacksonXmlProperty(localName = "OldStatus")
    private String oldStatus;
    @JacksonXmlProperty(localName = "NewStatus")
    private String newStatus;

}
