package com.weststein.integration;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;

@FeignClient(
        name = "application-resource",
        url = "https://staging.prepaidfinancialservices.com/accountapiv2/service.asmx",
        fallback = ApplicationResourceFallback.class

)
public interface ApplicationResource {

    @RequestMapping(method = RequestMethod.GET, path = "Process?Data={data}&APISigniture={method}")
    LinkedHashMap get(@PathVariable("data") String data, @PathVariable("method") String method);

}
