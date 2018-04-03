package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Operation;

public interface OperationService {

	Operation saveOperation(Operation operation);
	Iterable<Operation> listAllOperations();
	Operation getOperationById(Integer id);
}
