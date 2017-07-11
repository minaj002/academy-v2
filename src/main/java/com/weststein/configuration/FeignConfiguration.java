package com.weststein.configuration;

import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Client client() {
        return new ApacheHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new SmsErrorDecoder();
    }

    @Bean
    public Decoder feignDecoder() {
        CustomDecoder decoder = new CustomDecoder();
        return decoder;
    }

}
