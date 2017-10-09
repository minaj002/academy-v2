package com.academy.core.command.handler;

import com.academy.core.command.EditPaymentCommand;
import com.academy.core.command.result.EditPaymentResult;
import com.academy.core.domain.Payment;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditPaymentHandler implements CommandHandler<EditPaymentCommand, EditPaymentResult> {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public EditPaymentResult execute(EditPaymentCommand command) {

        Payment payment = paymentRepository.save(objectMapper.map(command.getPayment(), Payment.class));

        return new EditPaymentResult(payment.getId());
    }


}
