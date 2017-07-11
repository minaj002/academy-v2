package com.weststein.integration;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class PfsResourceFallback implements PFSResource {

    @Override
    public LinkedHashMap get(String data, String method) {
        return null;
    }
}
