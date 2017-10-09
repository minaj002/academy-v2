package com.academy.core.query.handler;

import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.Payment;
import com.academy.core.dto.PaymentBean;
import com.academy.core.query.GetPaymentsForMonthQuery;
import com.academy.core.query.result.PaymentsForMonthResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class GetPaymentsForMonthHandler implements QueryHandler<GetPaymentsForMonthQuery, PaymentsForMonthResult> {

    @Autowired
    AcademyUserRepository academyUserRepository;

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public PaymentsForMonthResult execute(GetPaymentsForMonthQuery query) {

        AcademyUser user = academyUserRepository.findByName(query.getUserName());

        Date date = query.getPaymentsForMonth();
        LocalDate startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);

        List<Payment> payments = paymentRepository.findByAcademyNameAndPaymentDateBetween(user.getAcademy().getName(), Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        List<PaymentBean> paymentBeans = objectMapper.map(payments, PaymentBean.class);

        PaymentsForMonthResult result = new PaymentsForMonthResult();
        result.setPayments(paymentBeans);
        return result;
    }

}
