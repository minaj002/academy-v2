package com.weststein.integration.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AccountAPIv2ViewStatement extends AccountAPIv2 {

    @JacksonXmlProperty(localName = "ViewStatement")
    private ViewStatementResponse viewStatement;

}
