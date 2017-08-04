package com.weststein.repository.business;

import org.springframework.data.repository.CrudRepository;

public interface CompanyInformationRepository extends CrudRepository<CompanyInformation, Long> {
    CompanyInformation findByBusinessId(Long businessId);
}
