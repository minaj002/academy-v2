package com.weststein.integration;

import com.weststein.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        name = "identification-resource",
        url = "https://api.solaris-sandbox.de/v1",
        configuration = FeignConfiguration.class
        , fallback = IdentificationResourceFallback.class
)
public interface IdentificationResource {

    @RequestMapping(method = RequestMethod.GET, path = "/persons/{personId}/identifications")
    List<SolarisIdentification> getAll(@PathVariable("personId") String personId);

    @RequestMapping(method = RequestMethod.GET, path = "/persons/{personId}/identifications/{id}")
    SolarisIdentification getIdentification(@PathVariable("personId") String personId, @PathVariable("id") String id);

}
