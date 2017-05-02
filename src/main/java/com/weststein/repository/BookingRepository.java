package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Booking findBySolarisId(String solarisId);
    List<Booking> findAllBySolarisIdIn(List<String> ids);
    List<Booking> findByAccountId(String accountId);

}
