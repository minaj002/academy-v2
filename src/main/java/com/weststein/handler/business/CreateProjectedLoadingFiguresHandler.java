package com.weststein.handler.business;

import com.weststein.controller.secured.model.business.ProjectedLoadingFiguresModel;
import com.weststein.infrastructure.OrikoObjectMapper;
import com.weststein.repository.ProjectedLoadingFigures;
import com.weststein.repository.ProjectedLoadingFiguresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateProjectedLoadingFiguresHandler {

    @Autowired
    private ProjectedLoadingFiguresRepository projectedLoadingFiguresRepository;

    @Autowired
    private OrikoObjectMapper objectMapper;

    public void handle(Long businessId, ProjectedLoadingFiguresModel projectedLoadingFiguresModel) {

        ProjectedLoadingFigures projectedLoadingFigures = objectMapper.map(projectedLoadingFiguresModel, ProjectedLoadingFigures.class);
        projectedLoadingFigures.setBusinessId(businessId);
        projectedLoadingFigures.setCreated(LocalDateTime.now());

        projectedLoadingFiguresRepository.save(projectedLoadingFigures);

    }

}
