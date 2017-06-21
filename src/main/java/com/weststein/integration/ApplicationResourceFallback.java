package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class ApplicationResourceFallback implements ApplicationResource {


    @Override
    public LinkedHashMap get(String data, String method) {
        return null;
    }
}
