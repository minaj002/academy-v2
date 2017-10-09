package com.academy.core.security;

import com.academy.core.domain.Academy;
import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.Section;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.academy.core.domain.AcademyUser.Role.ROLE_ADMIN;

@Component
public class DataInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AcademyUserRepository academyUserRepository;
    @Autowired
    private SectionRepository sectionRepository;

//	@Autowired
//	PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        logger.info("Initializing Default data");

        if (academyUserRepository.count() == 0) {

            AcademyUser user = new AcademyUser();
            user.setActive(true);
            user.setName("JevBjj@mail.com");
            user.setPassword("password");
            user.addRole(AcademyUser.Role.ROLE_OWNER);
//			user.setPassword(passwordEncoder.encode("password"));

            Academy academy = new Academy();
            academy.setEmail("JevBjj@mail.com");
            academy.setName("BJJAcademy");
            Section section = new Section();
            section.setName("BJJ");
            section = sectionRepository.save(section);
            academy.getSections().add(section);
            user.setAcademy(academy);
            user.addRole(ROLE_ADMIN);

            academyUserRepository.save(user);

        } else {
            Iterable<AcademyUser> ac = academyUserRepository.findAll();


            List<AcademyUser> users = StreamSupport.<AcademyUser>stream(ac.spliterator(), false).collect(Collectors.toList());
            for (AcademyUser academyUser : users) {
                logger.info("User: " + academyUser.getName());
                logger.info("Password: " + academyUser.getPassword());
            }
            logger.info("Default data already initialized");
        }
    }

}
