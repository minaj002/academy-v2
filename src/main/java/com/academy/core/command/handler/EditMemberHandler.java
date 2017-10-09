package com.academy.core.command.handler;

import com.academy.core.command.EditMemberCommand;
import com.academy.core.command.result.EditMemberResult;
import com.academy.core.domain.Member;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditMemberHandler implements CommandHandler<EditMemberCommand, EditMemberResult> {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public EditMemberResult execute(EditMemberCommand command) {

        Member member = objectMapper.map(command.getMember(), Member.class);
        Member existingMember = memberRepository.findOne(member.getId());
        updateMemberInfo(existingMember, member);
        memberRepository.save(existingMember);

        return new EditMemberResult(member.getId());
    }

    private void updateMemberInfo(Member existingMember, Member member) {

        existingMember.setFirstName(member.getFirstName());
        existingMember.setLastName(member.getLastName());
        existingMember.setDateOfBirth(member.getDateOfBirth());
        existingMember.setEmail(member.getEmail());
        existingMember.setPhone(member.getPhone());
        existingMember.setAddress(member.getAddress());
    }

}
