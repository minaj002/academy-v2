package com.academy.repository;

import com.academy.core.domain.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

//public interface PaymentRepository extends MongoRepository<Payment, String> {
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByMemberIdAndPaymentDateBetweenOrderByPaidUntilDesc(Long memberId, Date start, Date end);

    List<Payment> findByAcademyNameAndPaymentDateBetween(String academyName, Date start, Date end);

}
