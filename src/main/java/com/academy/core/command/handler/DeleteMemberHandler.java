package com.academy.core.command.handler;

import com.academy.core.command.DeleteMemberCommand;
import com.academy.core.command.result.DeleteMemberResult;
import com.academy.core.domain.Member;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteMemberHandler implements CommandHandler<DeleteMemberCommand, DeleteMemberResult> {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public DeleteMemberResult execute(DeleteMemberCommand command) {

	Member member = objectMapper.map(command.getMember(), Member.class);
	memberRepository.delete(member);

	return new DeleteMemberResult(member.getId());
    }

}
