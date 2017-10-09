package com.academy.core.command.handler;


import com.academy.core.command.AddSectionToAcademyCommand;
import com.academy.core.command.result.AddSectionResult;
import com.academy.core.domain.Academy;
import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.Section;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyRepository;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddSectionToAcademyHandler implements CommandHandler<AddSectionToAcademyCommand, AddSectionResult> {

	@Autowired
	AcademyRepository academyRepository;
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	AcademyUserRepository academyUserRepository;

	@Autowired
	private OrikoObjectMapper objectMapper;
	
	@Override
	public AddSectionResult execute(AddSectionToAcademyCommand command) {


		AcademyUser academyUser = academyUserRepository.findByName(command.getUserName());
		Academy academy = academyUser.getAcademy();
		Section section = sectionRepository.save(objectMapper.map(command.getSection(), Section.class));
		academy.getSections().add(section);
		academy = academyRepository.save(academy);

		return new AddSectionResult(section.getId());
	}
	
}
