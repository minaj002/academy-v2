package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

public interface BusinessProfileRepository extends CrudRepository<BusinessProfile, Long> {

    BusinessProfile findByBusinessId(Long businessId);

}
