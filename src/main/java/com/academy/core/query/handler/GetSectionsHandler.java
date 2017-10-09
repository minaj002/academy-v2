package com.academy.core.query.handler;

import com.academy.core.domain.Section;
import com.academy.core.dto.SectionBean;
import com.academy.core.query.GetSectionsQuery;
import com.academy.core.query.result.GetSectionsResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GetSectionsHandler implements	QueryHandler<GetSectionsQuery, GetSectionsResult> {

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private OrikoObjectMapper objectMapper;

	@Override
	public GetSectionsResult execute(GetSectionsQuery query) {
		List<Section> sections = StreamSupport.stream(sectionRepository.findAll().spliterator(), false).collect(Collectors.toList());
		return GetSectionsResult.builder().sections(objectMapper.map(sections, SectionBean.class)).build();
	}

}
