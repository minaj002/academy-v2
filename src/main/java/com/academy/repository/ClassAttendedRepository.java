package com.academy.repository;

import com.academy.core.domain.Academy;
import com.academy.core.domain.ClassAttended;
import com.academy.core.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ClassAttendedRepository extends CrudRepository<ClassAttended, Long> {

    List<ClassAttended> findByAcademyAndDateIsBetween(Academy academy, Date dateStart, Date end);
    List<ClassAttended> findByMembersContainingOrderByDateDesc(Member member);

}
