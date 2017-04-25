package com.weststein.integration;

import com.weststein.configuration.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.GET;
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

    @RequestMapping(method = RequestMethod.GET, path = "/persons/{personId}")
    FullPerson getPerson(@PathVariable("personId")String personId);

}
