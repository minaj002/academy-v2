package com.weststein.integration;

import com.weststein.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        name = "person-resource",
        url = "https://api.solaris-sandbox.de/v1",
        configuration = FeignConfiguration.class
        ,fallback = PersonResourceFallback.class

)
public interface PersonResource {

    @RequestMapping(method = RequestMethod.GET, path = "/persons")
    List<Person> getAll();

}
