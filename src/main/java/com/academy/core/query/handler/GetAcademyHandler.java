package com.academy.core.query.handler;

import com.academy.core.domain.Academy;
import com.academy.core.dto.AcademyBean;
import com.academy.core.query.GetAcademyQuery;
import com.academy.core.query.result.GetAcademiesResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetAcademyHandler implements	QueryHandler<GetAcademyQuery, GetAcademiesResult> {

	@Autowired
	private AcademyUserRepository academyUserRepository;

	@Autowired
	private OrikoObjectMapper objectMapper;

	@Override
	public GetAcademiesResult execute(GetAcademyQuery query) {
		Academy academy = academyUserRepository.findByName(query.getName()).getAcademy();
		AcademyBean academyBean = objectMapper.map(academy, AcademyBean.class);
		ArrayList<AcademyBean> academyList = new ArrayList<AcademyBean>();
		academyList.add(academyBean);
		return new GetAcademiesResult(academyList);
	}

}
