package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

public interface ProjectedLoadingFiguresRepository extends CrudRepository<ProjectedLoadingFigures, Long> {

    ProjectedLoadingFigures findByBusinessId(Long businessId);

}
