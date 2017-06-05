package com.weststein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findBySolarisId(String solarisId);
    List<Booking> findAllBySolarisIdIn(List<String> ids);
    Page<Booking> findByAccountId(String accountId, Pageable pageable);

}
