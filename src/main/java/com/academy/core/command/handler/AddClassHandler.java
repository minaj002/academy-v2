package com.academy.core.command.handler;


import com.academy.core.command.AddClassCommand;
import com.academy.core.command.result.AddClassResult;
import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.ClassAttended;
import com.academy.core.domain.Member;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.ClassAttendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddClassHandler implements CommandHandler<AddClassCommand, AddClassResult> {

	@Autowired
	ClassAttendedRepository classAttendedRepository;
	@Autowired
	AcademyUserRepository academyUserRepository;
	@Autowired
	private OrikoObjectMapper objectMapper;

	@Override
	public AddClassResult execute(AddClassCommand command) {
		
		
		ClassAttended classAttended = toClassAttended(command);
		
		classAttended=classAttendedRepository.save(classAttended);
		
		return new AddClassResult(classAttended.getId());
	}

	private ClassAttended toClassAttended(AddClassCommand command) {
		ClassAttended classAttended= new ClassAttended();
		classAttended.setDate(command.getDate());
		
		AcademyUser user = academyUserRepository.findByName(command.getUserName());
		classAttended.setAcademy(user.getAcademy());
		
		List<Member> members =objectMapper.map(command.getMembers(), Member.class);
		classAttended.setMembers(members);
		return classAttended;
	}

}
