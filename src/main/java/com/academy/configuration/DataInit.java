package com.academy.configuration;

import com.academy.core.domain.Academy;
import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.Section;
import com.academy.repository.AcademyRepository;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit {

    @Autowired
    private AcademyUserRepository academyUserRepository;
    @Autowired
    private AcademyRepository academyRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void init() {
        if (academyUserRepository.findByName("minaj002@gmail.com") == null) {

            Section section = Section.builder().name("BJJ").build();
            section = sectionRepository.save(section);
            List<Section> sections = new ArrayList<>();
            sections.add(section);
            Academy academy = new Academy();
            academy.setEmail("minaj002@gmail.com");
            academy.setName("BJJAcademy.lv");
            academy.setSections(sections);
            academy = academyRepository.save(academy);
            List<AcademyUser.Role> roles = new ArrayList<>();
            roles.add(AcademyUser.Role.ROLE_OWNER);
            AcademyUser user = new AcademyUser();
            user.setAcademy(academy);
            user.setActive(true);
            user.setName("minaj002@gmail.com");
            user.setPassword(passwordEncoder.encode("1202password"));
            user.setRoles(roles);
            academyUserRepository.save(user);
        }
        if (academyUserRepository.findByName("mi@karate.lv") == null) {

            Section section = Section.builder().name("KARATE").build();
            section = sectionRepository.save(section);
            List<Section> sections = new ArrayList<>();
            sections.add(section);
            Academy academy = new  Academy();
            academy.setEmail("mi@karate.lv");
            academy.setName("BKK");
            academy.setSections(sections);
            academy = academyRepository.save(academy);
            List<AcademyUser.Role> roles = new ArrayList<>();
            roles.add(AcademyUser.Role.ROLE_OWNER);
            AcademyUser user = new AcademyUser();
            user.setAcademy(academy);
            user.setActive(true);
            user.setName("mi@karate.lv");
            user.setPassword(passwordEncoder.encode("2103password"));
            user.setRoles(roles);
            academyUserRepository.save(user);
        }
    }

}
