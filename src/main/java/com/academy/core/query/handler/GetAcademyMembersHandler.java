package com.academy.core.query.handler;

import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.Member;
import com.academy.core.dto.MemberBean;
import com.academy.core.query.GetAcademyMembersQuery;
import com.academy.core.query.result.GetAcademyMembersResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAcademyMembersHandler implements
        QueryHandler<GetAcademyMembersQuery, GetAcademyMembersResult> {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AcademyUserRepository academyUserRepository;

    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public GetAcademyMembersResult execute(GetAcademyMembersQuery query) {
        AcademyUser user = academyUserRepository.findByName(query.getUser());
        List<Member> members = memberRepository.findByAcademyName(user.getAcademy().getName());
        List<MemberBean> memberBeans = objectMapper.map(members, MemberBean.class);
        GetAcademyMembersResult result = new GetAcademyMembersResult();
        result.setMembers(memberBeans);
        return result;

    }

}
