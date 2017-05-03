package com.weststein.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weststein.integration.SolarisIdentification;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SynchronizationHandlerTest {


    @Test
    public void testConverter() throws IOException {

        MappingJackson2HttpMessageConverter conv = new MappingJackson2HttpMessageConverter();

        String body = "{\n" +
                "  \"id\": \"5025324c8e248863de844cb2fee0c4f3cidt\",\n" +
                "  \"reference\": \"TST-PLVWT\",\n" +
                "  \"url\": \"https://go.test.idnow.de/solarisbankvideoidentsandbox/identifications/5025324c8e248863de844cb2fee0c4f3cidt\",\n" +
                "  \"status\": \"successful\",\n" +
                "  \"completed_at\": \"2016-12-06T13:11:48.000Z\",\n" +
                "  \"method\": \"idnow\",\n" +
                "  \"address\": null,\n" +
                "  \"documents\": [\n" +
                "    {\n" +
                "      \"id\": \"0d0238ae43f6dac2fcc3439373bbd55ccdoc\",\n" +
                "      \"name\": \"5025324c8e248863de844cb2fee0c4f3cidt.pdf\",\n" +
                "      \"content_type\": \"application/pdf\",\n" +
                "      \"document_type\": \"KYC_REPORT\",\n" +
                "      \"size\": 533701,\n" +
                "      \"customer_accessible\": false\n" +
                "    }]}";

        HttpInputMessage inputMessage = new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {


                return new ByteArrayInputStream(body.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                return null;
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        SolarisIdentification res = objectMapper.readValue(new ByteArrayInputStream(body.getBytes()), SolarisIdentification.class);
        res.getAddress();

        Object result = conv.read(SolarisIdentification.class, inputMessage);
        res.toString();

    }

}