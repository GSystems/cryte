package com.eu.gsys.infrastructure.repositories;

import com.eu.gsys.infrastructure.entities.OperationEntity;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<OperationEntity, Integer> {
}
