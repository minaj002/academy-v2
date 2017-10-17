package com.academy.core.query.handler;

import com.academy.core.domain.ClassAttended;
import com.academy.core.domain.Member;
import com.academy.core.dto.ClassAttendedBean;
import com.academy.core.query.GetClassesForMemberQuery;
import com.academy.core.query.result.GetClassesForMemberResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.ClassAttendedRepository;
import com.academy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetClassesAttendedForMemberHandler implements QueryHandler<GetClassesForMemberQuery, GetClassesForMemberResult> {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    ClassAttendedRepository classAttendedRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public GetClassesForMemberResult execute(GetClassesForMemberQuery query) {

        Member member = memberRepository.findOne(query.getMemberId());
        List<ClassAttended> classesAttended = classAttendedRepository.findByMembersContainingOrderByDateDesc(member);
        List<ClassAttendedBean> classAttendedBeans = objectMapper.map(classesAttended, ClassAttendedBean.class);

        return new GetClassesForMemberResult(classAttendedBeans);
    }

}
