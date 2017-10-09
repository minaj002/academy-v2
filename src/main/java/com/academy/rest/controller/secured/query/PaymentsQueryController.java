package com.academy.rest.controller.secured.query;

import com.academy.core.query.GetPaymentsForMonthQuery;
import com.academy.core.query.PaymentsForPeriodForMemberQuery;
import com.academy.core.query.result.PaymentsForMonthResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.api.Payment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/payments")
public class PaymentsQueryController {

    private static Logger LOG = LoggerFactory.getLogger(PaymentsQueryController.class);

    @Autowired
    QueryService queryService;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/{date}")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Collection<Payment> getPaymentsForMonth(@PathVariable Date date) {

        String name = getUserName();

        LOG.debug("Getting payments for {}", name);

        GetPaymentsForMonthQuery query = new GetPaymentsForMonthQuery(name, date);
        PaymentsForMonthResult payments = queryService.execute(query);
        List<Payment> paymentsJson = objectMapper.map(payments.getPayments(), Payment.class);
        return paymentsJson;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{date}/{period}/{id}")
    @ResponseBody
    public Collection<Payment> getPaymentsForMonthsForMember(@PathVariable Date date, @PathVariable Integer period, @PathVariable Long id) {

        String name = getUserName();

        PaymentsForPeriodForMemberQuery query = PaymentsForPeriodForMemberQuery.create(name).forMember(id).forPeriod(period).untilMonth(date);
        PaymentsForMonthResult payments = queryService.execute(query);

        List<Payment> paymentsJson = objectMapper.map(payments.getPayments(), Payment.class);
        return paymentsJson;
    }

    private String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
