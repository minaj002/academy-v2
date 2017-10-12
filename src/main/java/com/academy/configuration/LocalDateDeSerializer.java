package com.academy.configuration;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class LocalDateDeSerializer extends StdDeserializer<Date> {

    public LocalDateDeSerializer() {
        super(LocalDate.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(p.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
