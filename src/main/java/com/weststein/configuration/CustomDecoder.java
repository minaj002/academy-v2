package com.weststein.configuration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.weststein.integration.sms.SmsResponse;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

public class CustomDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (response.status() == 404) return Util.emptyValueOf(type);
        if (response.body() == null) return null;
        Reader reader = response.body().asReader();

        if (SmsResponse.class.equals(type)) {
            return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create().fromJson(reader, type);
        }
        if (LinkedHashMap.class.equals(type)) {
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(response.body().asReader(), LinkedHashMap.class);
        } else {
            return null;
        }
    }
}
