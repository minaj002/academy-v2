package com.weststein.integration;

import com.weststein.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(
        name = "account-resource",
        url = "https://api.solaris-sandbox.de/v1",
        configuration = FeignConfiguration.class
        ,fallback = AccountResourceFallback.class

)
public interface AccountResource {

    @RequestMapping(method = RequestMethod.GET, path = "/persons/{personId}/accounts")
    List<SolarisAccount> getAccounts(@PathVariable("personId") String personId);

    @RequestMapping(method = RequestMethod.GET, path = "/persons/{personId}/accounts/{accountId}")
    SolarisAccount getAccount(@PathVariable("personId") String personId, @PathVariable("accountId") String accountId);

    @RequestMapping(method = RequestMethod.GET, path = "/persons/{personId}/accounts/{accountId}/bookings")
    List<SolarisBooking> getAccountBookings(@PathVariable("personId") String personId, @PathVariable("accountId") String accountId );

}
