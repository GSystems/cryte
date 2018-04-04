package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Operation;
import com.eu.gsys.cryte.domain.models.Client;

public interface OperationService {

    Operation saveOperation(Operation deposit);
    Iterable<Operation> listAllOperations();
    Operation getOperationById(Integer id);
    void deleteOperation(Integer id);

    void addOperation(Client client, Operation operation);
}
