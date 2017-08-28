package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SentEmailRepository extends CrudRepository<SentEmail, Long> {

    List<SentEmail> findAllByEmailOrderBySendingTimeDesc(String email);

}
