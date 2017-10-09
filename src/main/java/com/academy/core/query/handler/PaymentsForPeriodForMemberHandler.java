package com.academy.core.query.handler;

import com.academy.core.domain.Payment;
import com.academy.core.dto.PaymentBean;
import com.academy.core.query.PaymentsForPeriodForMemberQuery;
import com.academy.core.query.result.PaymentsForMonthResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class PaymentsForPeriodForMemberHandler implements QueryHandler<PaymentsForPeriodForMemberQuery, PaymentsForMonthResult> {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public PaymentsForMonthResult execute(PaymentsForPeriodForMemberQuery query) {

        Date date = query.getPaymentsForMonth();

        LocalDate startDate  = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusMonths(query.getPeriod());

        List<Payment> payments = paymentRepository.findByMemberIdAndPaymentDateBetweenOrderByPaidUntilDesc(query.getMember(), Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), date);

        List<PaymentBean> paymentBeans = objectMapper.map(payments, PaymentBean.class);

        PaymentsForMonthResult result = new PaymentsForMonthResult();
        result.setPayments(paymentBeans);
        return result;
    }

}
