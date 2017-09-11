package com.weststein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SepaTransferRepository extends PagingAndSortingRepository<SepaTransfer, Long> {

    Page<SepaTransfer> findAllByFromAndStatusIsNotOrderByIdDesc(CardholderId cardholderId, SepaTransfer.Status status, Pageable pageable);

}
