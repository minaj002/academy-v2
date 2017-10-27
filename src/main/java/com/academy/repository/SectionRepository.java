package com.academy.repository;

import com.academy.core.domain.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Long> {

    Section findByName(String name);
	
}
