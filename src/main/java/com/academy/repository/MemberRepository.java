package com.academy.repository;

import com.academy.core.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

	List<Member> findByAcademyNameOrderByFirstName(String academyName);
	
}
