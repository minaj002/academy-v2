package com.weststein.repository.business;

import org.springframework.data.repository.CrudRepository;

public interface CardIbanRepository extends CrudRepository<CardIban, Long> {

    CardIban findByBusinessId(Long businessId);

}
