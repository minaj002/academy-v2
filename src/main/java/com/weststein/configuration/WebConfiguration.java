package com.weststein.configuration;

import com.weststein.infrastructure.logger.WeststeinLoggingFilter;
import com.weststein.repository.AuditRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private AuditRecordRepository auditRecordRepository;

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
                        "http://localhost:3000",
                        "http://ec2-52-57-94-206.eu-central-1.compute.amazonaws.com",
                        "http://52.57.94.206" // Public ipv4
                ).allowedMethods("GET", "OPTIONS", "POST", "PATCH", "PUT", "DELETE");
            }
        };
    }

    @Bean
    public WeststeinLoggingFilter requestLoggingFilter() {
        WeststeinLoggingFilter loggingFilter = new WeststeinLoggingFilter(auditRecordRepository);
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        return loggingFilter;
    }

}
