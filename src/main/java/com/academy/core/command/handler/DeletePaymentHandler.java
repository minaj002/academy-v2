package com.academy.core.command.handler;

import com.academy.core.command.DeletePaymentCommand;
import com.academy.core.command.result.DeletePaymentResult;
import com.academy.core.domain.Payment;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePaymentHandler implements CommandHandler<DeletePaymentCommand, DeletePaymentResult> {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public DeletePaymentResult execute(DeletePaymentCommand command) {

        paymentRepository.delete(objectMapper.map(command.getPayment(), Payment.class));

        return new DeletePaymentResult(command.getPayment().getId());
    }

}
