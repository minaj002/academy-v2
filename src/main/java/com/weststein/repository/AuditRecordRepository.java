package com.weststein.repository;

import org.springframework.data.repository.CrudRepository;

public interface AuditRecordRepository extends CrudRepository<AuditRecord, Long> {
}
