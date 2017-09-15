package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by artis on 08/09/2017.
 */

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findByRiskLevelNot(Country.RiskLevel riskLevel);

}
