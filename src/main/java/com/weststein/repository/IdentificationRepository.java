package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IdentificationRepository extends CrudRepository<Identification, Long> {

    Identification findBySolarisId(String solarisId);

    List<Identification> findAllBySolarisIdIn(List<String> ids);

    List<Identification> findAllByPersonId(String id);

}
