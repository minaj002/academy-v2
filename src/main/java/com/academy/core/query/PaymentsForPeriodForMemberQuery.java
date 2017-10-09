package com.academy.core.query;

import com.academy.core.query.result.PaymentsForMonthResult;

import java.util.Date;

public class PaymentsForPeriodForMemberQuery implements Query<PaymentsForMonthResult> {

    private String userName;
    private Date untilMonth;
    private Integer period;
    private Long member;

    private PaymentsForPeriodForMemberQuery(String userName) {
        this.userName = userName;
    }

    public static PaymentsForPeriodForMemberQuery create(String userName) {
        return new PaymentsForPeriodForMemberQuery(userName);
    }

    public PaymentsForPeriodForMemberQuery untilMonth(Date untilMonth) {
        this.untilMonth = untilMonth;
        return this;
    }

    public PaymentsForPeriodForMemberQuery forPeriod(Integer period) {
        this.period = period;
        return this;
    }

    public PaymentsForPeriodForMemberQuery forMember(Long member) {
        this.member = member;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public Long getMember() {
        return member;
    }

    public String getUserName() {
        return userName;
    }

    public Date getPaymentsForMonth() {
        return untilMonth;
    }

}
