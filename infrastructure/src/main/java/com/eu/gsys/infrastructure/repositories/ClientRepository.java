package com.eu.gsys.infrastructure.repositories;

import com.eu.gsys.infrastructure.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
}
