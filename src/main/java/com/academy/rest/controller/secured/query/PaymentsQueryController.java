package com.academy.rest.controller.secured.query;

import com.academy.core.query.GetPaymentsForMonthQuery;
import com.academy.core.query.PaymentsForPeriodForMemberQuery;
import com.academy.core.query.result.PaymentsForMonthResult;
import com.academy.core.query.service.QueryService;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.rest.ResponseWrapper;
import com.academy.rest.api.Payment;
import com.academy.security.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/payments")
public class PaymentsQueryController {

    @Autowired
    QueryService queryService;
    @Autowired
    private OrikoObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/{date}")
    @ResponseBody
    @ApiOperation(value = "get all requested users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public ResponseWrapper<List<Payment>> getPaymentsForMonth(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {

        GetPaymentsForMonthQuery query = new GetPaymentsForMonthQuery(userService.getUserName(), date);
        PaymentsForMonthResult payments = queryService.execute(query);
        List<Payment> paymentsJson = objectMapper.map(payments.getPayments(), Payment.class);
        return ResponseWrapper.<List<Payment>>builder().response(paymentsJson).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{date}/{period}/{id}")
    @ResponseBody
    public ResponseWrapper<List<Payment>> getPaymentsForMonthsForMember(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date, @PathVariable Integer period, @PathVariable Long id) {

        PaymentsForPeriodForMemberQuery query = PaymentsForPeriodForMemberQuery.create(userService.getUserName()).forMember(id).forPeriod(period).untilMonth(date);
        PaymentsForMonthResult payments = queryService.execute(query);

        List<Payment> paymentsJson = objectMapper.map(payments.getPayments(), Payment.class);
        return ResponseWrapper.<List<Payment>>builder().response(paymentsJson).build();
    }

}
