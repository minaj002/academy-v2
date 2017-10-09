package com.academy.core.query.handler;

import com.academy.core.domain.Academy;
import com.academy.core.dto.AcademyBean;
import com.academy.core.query.GetAcademiesQuery;
import com.academy.core.query.result.GetAcademiesResult;
import com.academy.infrastructure.OrikoObjectMapper;
import com.academy.repository.AcademyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GetAcademiesHandler implements QueryHandler<GetAcademiesQuery, GetAcademiesResult> {

    @Autowired
    AcademyRepository academyRepository;
    @Autowired
    private OrikoObjectMapper objectMapper;

    @Override
    public GetAcademiesResult execute(GetAcademiesQuery query) {
        List<Academy> academies = StreamSupport.stream(academyRepository.findAll().spliterator(), false).collect(Collectors.toList());
        List<AcademyBean> academyBeans = objectMapper.map(academies, AcademyBean.class);
        return new GetAcademiesResult(academyBeans);
    }

}
