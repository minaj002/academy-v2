package com.academy.core.query.handler;

import com.academy.core.domain.AcademyUser;
import com.academy.core.domain.ClassAttended;
import com.academy.core.dto.ClassAttendedBean;
import com.academy.core.query.GetClassesForDateQuery;
import com.academy.core.query.result.GetClassesForDateResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyUserRepository;
import com.academy.repository.ClassAttendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class GetClassesAttendedForDateHandler implements QueryHandler<GetClassesForDateQuery, GetClassesForDateResult> {

    @Autowired
    AcademyUserRepository academyUserRepository;

    @Autowired
    ClassAttendedRepository classAttendedRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public GetClassesForDateResult execute(GetClassesForDateQuery query) {

        AcademyUser user = academyUserRepository.findByName(query.getUser());

        Date date = query.getDate();
        LocalDate startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);

        List<ClassAttended> classesAttended = classAttendedRepository.findByAcademyAndDateIsBetween(user.getAcademy(), Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        List<ClassAttendedBean> classAttendedBeans = objectMapper.map(classesAttended, ClassAttendedBean.class);

        return new GetClassesForDateResult(classAttendedBeans);
    }

}
