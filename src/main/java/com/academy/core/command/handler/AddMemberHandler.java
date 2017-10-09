package com.academy.core.command.handler;

import com.academy.core.command.AddMemberCommand;
import com.academy.core.command.result.AddMemberResult;
import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.Member;
import com.academy.core.dto.MemberBean;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AddMemberHandler implements CommandHandler<AddMemberCommand, AddMemberResult> {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AcademyUserRepository academyUserRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public AddMemberResult execute(AddMemberCommand command) {

        AcademyUser user = academyUserRepository.findByName(command.getUserName());
        MemberBean memberBean = command.getMember();
        Member member = objectMapper.map(memberBean, Member.class);
        member.setJoinDate(new Date());
        member.setAcademyName(user.getAcademy().getName());
        memberRepository.save(member);

        return new AddMemberResult(member.getId());
    }

}
