package com.academy.core.command.handler;

import com.academy.core.command.DeleteClassCommand;
import com.academy.core.command.result.DeleteClassResult;
import com.academy.core.domain.ClassAttended;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.ClassAttendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteClassHandler implements CommandHandler<DeleteClassCommand, DeleteClassResult> {

    @Autowired
    ClassAttendedRepository classAttendedRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public DeleteClassResult execute(DeleteClassCommand command) {

        ClassAttended classAttended = objectMapper.map(command.getClassAttended(), ClassAttended.class);
        classAttendedRepository.delete(classAttended);
        return new DeleteClassResult(classAttended.getId());
    }

}
