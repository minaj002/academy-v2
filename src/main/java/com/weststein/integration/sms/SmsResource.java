package com.weststein.integration.sms;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "application-resource",
        url = "https://traffic.sales.lv/API:0.14/",
        fallback = SmsResourceFallback.class

)
public interface SmsResource {

    @RequestMapping(method = RequestMethod.GET, path = "?Number={number}&Content={message}")
    String get(@PathVariable("number") String phoneNumber, @PathVariable("message") String message);

}

