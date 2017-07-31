package com.weststein.integration.sms;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weststein.infrastructure.exceptions.SmsError;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
@Ignore
public class SmsServiceImplTest {

    @Test
    public void testParseError() {
        Gson enc = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        SmsError error = new SmsError();
        error.setError("phone");
        assertEquals("{\"Error\":\"phone\"}", enc.toJson(error));
    }

}