package com.weststein.handler.country;

import com.weststein.repository.Country;
import com.weststein.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by artis on 15/09/2017.
 */

@Component
public class GetAvailableCountriesHandler {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> handle() {
        return countryRepository.findByRiskLevelNot(Country.RiskLevel.EXTREME);
    }

}
