package com.weststein.repository.business;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShareHolderRepository extends CrudRepository<ShareHolder, Long> {

    List<ShareHolder> findByBusinessId(Long businessId);

}
