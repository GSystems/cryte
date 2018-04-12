package com.eu.gsys.cryte.infrastructure.repositories;

import com.eu.gsys.cryte.infrastructure.entities.GenericDepositEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<GenericDepositEntity, Integer> {
}
