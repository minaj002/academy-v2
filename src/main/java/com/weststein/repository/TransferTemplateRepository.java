package com.weststein.repository;

import com.weststein.repository.business.BusinessInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransferTemplateRepository extends PagingAndSortingRepository<TransferTemplate, Long> {
    Page<TransferTemplate> findAllByBusinessOrderByName(BusinessInformation businessInformation, Pageable pageable);
}
