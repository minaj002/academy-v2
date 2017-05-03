package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findBySolarisId(String solarisId);
    List<Account> findAllBySolarisIdIn(List<String> ids);

    List<Account> findAllByPersonId(String personId);

}
