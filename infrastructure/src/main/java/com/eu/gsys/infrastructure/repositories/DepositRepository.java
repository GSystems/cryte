package com.eu.gsys.infrastructure.repositories;

import com.eu.gsys.infrastructure.entities.GenericDeposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<GenericDeposit, Integer> {
}
