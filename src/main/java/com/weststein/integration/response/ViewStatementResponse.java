package com.weststein.integration.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType()
public class ViewStatementResponse {

    private CardholderStatementDetails cardholderstatementdetails;

}
