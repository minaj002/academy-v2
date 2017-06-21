package com.weststein.integration.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class ViewStatement {

    @JacksonXmlProperty(localName = "Cardholderid")
    private String cardholderid;
    @JacksonXmlProperty(localName = "StartDate")
    private String startDate;
    @JacksonXmlProperty(localName = "EndDate")
    private String endDate;
    @JacksonXmlProperty(localName = "ViewStyle")
    private String viewStyle;

}
