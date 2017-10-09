package com.academy.core.command.handler;

import com.academy.core.command.AddPaymentCommand;
import com.academy.core.command.result.AddPaymentResult;
import com.academy.core.domain.Member;
import com.academy.core.domain.Payment;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.MemberRepository;
import com.academy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPaymentHandler implements CommandHandler<AddPaymentCommand, AddPaymentResult> {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public AddPaymentResult execute(AddPaymentCommand command) {

        Member member = memberRepository.findOne(command.getPayment().getMemberId());
        Payment payment = objectMapper.map(command.getPayment(), Payment.class);
        payment.setMemberId(member.getId());
        payment.setAcademyName(member.getAcademyName());
        payment = paymentRepository.save(payment);
        member.getPayments().add(payment);
        memberRepository.save(member);

        return new AddPaymentResult(member.getId());
    }

}
