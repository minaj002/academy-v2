package com.weststein.repository.business;

import org.springframework.data.repository.CrudRepository;

public interface ProjectedLoadingFiguresRepository extends CrudRepository<ProjectedLoadingFigures, Long> {

    ProjectedLoadingFigures findByBusinessId(Long businessId);

}
