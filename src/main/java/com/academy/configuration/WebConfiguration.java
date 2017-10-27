package com.academy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

import static com.academy.configuration.WebSecurityConfig.JWT_TOKEN_HEADER_PARAM;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private DataInit dataInit;

    @PostConstruct
    public void afterInit() {
        dataInit.init();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins(
                        "http://localhost:3000", "http://d14pn46jrbxu7g.cloudfront.net",
                        "http://academyv2.s3-website.eu-central-1.amazonaws.com"
                ).allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
                        .exposedHeaders(JWT_TOKEN_HEADER_PARAM);
            }
        };
    }


}
