package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

public interface BankAccountDetailsRepository extends CrudRepository<BankAccountDetails, Long> {
    BankAccountDetails findByBusinessId(Long businessId);
}
