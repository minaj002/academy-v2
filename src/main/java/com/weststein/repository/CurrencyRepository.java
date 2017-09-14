package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by artis on 11/09/2017.
 */

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    List<Currency> findByConvertOnline(Boolean convertOnline);

}
