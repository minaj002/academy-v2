package com.academy.repository;

import com.academy.core.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
//public interface MemberRepository extends MongoRepository<Member, String> {

	List<Member> findByAcademyName(String academyName);
	
}
