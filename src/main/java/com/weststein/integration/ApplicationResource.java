package com.weststein.integration;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "application-resource",
        url = "example.com",
        fallback = ApplicationResourceFallback.class

)
public interface ApplicationResource {

    @RequestMapping(method = RequestMethod.POST)
    ApplicationRequest apply(ApplicationRequest application);

}
