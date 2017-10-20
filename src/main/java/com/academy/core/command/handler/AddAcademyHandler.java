package com.academy.core.command.handler;


import com.academy.core.command.AddAcademyCommand;
import com.academy.core.command.result.AddAcademyResult;
import com.academy.core.domain.Academy;
import com.academy.core.domain.AcademyUser;
import com.academy.repository.AcademyRepository;
import com.academy.repository.AcademyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Function;

@Component
public class AddAcademyHandler implements CommandHandler<AddAcademyCommand, AddAcademyResult> {

    @Autowired
    AcademyRepository academyRepository;

    @Autowired
    AcademyUserRepository academyUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    private static final Function<AddAcademyCommand, Academy> ADD_ACADEMY_COMMAND_TO_ACADEMY = new AddAcademyCommandToAcademyFunction();

    @Override
    public AddAcademyResult execute(AddAcademyCommand command) {

        Academy academy = ADD_ACADEMY_COMMAND_TO_ACADEMY.apply(command);
        academy = academyRepository.save(academy);

        ArrayList<AcademyUser.Role> roles = new ArrayList<>();
        roles.add(AcademyUser.Role.ROLE_OWNER);
        AcademyUser academyUser = new AcademyUser();
        academyUser.setAcademy(academy);
        academyUser.setActive(true);
        academyUser.setName(academy.getEmail());
        academyUser.setPassword(encoder.encode(command.getPassword()));
        academyUser.setRoles(roles);
        academyUserRepository.save(academyUser);

        return new AddAcademyResult(academy.getId());
    }


    static class AddAcademyCommandToAcademyFunction implements Function<AddAcademyCommand, Academy> {

        @Override
        public Academy apply(AddAcademyCommand command) {
            Academy academy = new Academy();
            academy.setName(command.getName());
            academy.setEmail(command.getEmail());
            return academy;
        }

    }

}
