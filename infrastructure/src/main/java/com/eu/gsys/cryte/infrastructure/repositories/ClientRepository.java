package com.eu.gsys.cryte.infrastructure.repositories;

import com.eu.gsys.cryte.infrastructure.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
}
