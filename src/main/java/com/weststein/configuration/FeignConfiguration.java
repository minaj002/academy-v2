package com.weststein.configuration;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public Client client(){
        return new ApacheHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new SmsErrorDecoder();
    }

    @Bean
    public Decoder decoder() {
        GsonDecoder decoder = new GsonDecoder(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create());
        return decoder;
    }

}
