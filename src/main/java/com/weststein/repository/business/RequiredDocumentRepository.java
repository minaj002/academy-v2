package com.weststein.repository.business;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequiredDocumentRepository extends CrudRepository<RequiredDocument, Long> {
    List<RequiredDocument> findByBusinessId(Long businessId);
}
