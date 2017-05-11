package com.weststein.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("solaris")
public class HookSignatures {
    private Map<String, String> hook;

    public String getSignature(String type) {
        return hook.get(type.toLowerCase());
    }
}
