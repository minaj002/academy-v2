package com.academy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.academy.configuration.WebSecurityConfig.JWT_TOKEN_HEADER_PARAM;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

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
                        "http://localhost:3000", "http://d14pn46jrbxu7g.cloudfront.net"
                ).allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
//                .allowedHeaders("*")
                        .exposedHeaders(JWT_TOKEN_HEADER_PARAM);
            }
        };
    }


}
