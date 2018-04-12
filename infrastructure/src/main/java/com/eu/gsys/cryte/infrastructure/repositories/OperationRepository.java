package com.eu.gsys.cryte.infrastructure.repositories;

import com.eu.gsys.cryte.infrastructure.entities.OperationEntity;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<OperationEntity, Integer> {
}
