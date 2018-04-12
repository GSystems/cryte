package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Operation;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.cryte.infrastructure.entities.OperationEntity;

import java.util.ArrayList;
import java.util.List;

public class OperationTransformer {

	public static List<OperationEntity> fromOperationListToEntity(List<Operation> operations) {
		List<OperationEntity> operationEntities = new ArrayList<>();

		for (Operation operation : operations) {
			operationEntities.add(fromOperationToEntity(operation));
		}

		return operationEntities;
	}

	public static OperationEntity fromOperationToEntity(Operation operation) {
		OperationEntity operationEntity = new OperationEntity();

		operationEntity.setId(operation.getId());
		operationEntity.setDate(operation.getDate());
		operationEntity.setCoinQty(operation.getCoinQty());
		operationEntity.setCoinName(operation.getCoinType().name());
		operationEntity.setCoinId(operation.getCoinType().getCode());
		operationEntity.setFeePerc(operation.getFeePerc());
		operationEntity.setFeeCtv(operation.getFeeCtv());
		operationEntity.setMinimumWithdrawalCoinPrice(operation.getMinimumWithdrawalCoinPrice());
		operationEntity.setOperationCtv(operation.getOperationCtv());
		operationEntity.setCoinPrice(operation.getCoinPrice());
		operationEntity.setOperationType(operation.getOperationType().name());

		return operationEntity;
	}

	public static List<Operation> toOperationListFromEntity(
			Iterable<OperationEntity> operationEntities) {
		List<Operation> operationList = new ArrayList<>();

		for (OperationEntity entity : operationEntities) {
			operationList.add(toOperationFromEntity(entity));
		}

		return operationList;
	}

	public static Operation toOperationFromEntity(OperationEntity operationEntity) {
		Operation operation = new Operation();

		operation.setId(operationEntity.getId());
		operation.setDate(operationEntity.getDate());
		operation.setCoinQty(operationEntity.getCoinQty());
		operation.setCoinType(CoinType.valueOf(operationEntity.getCoinName()));
		operation.setFeePerc(operationEntity.getFeePerc());
		operation.setFeeCtv(operationEntity.getFeeCtv());
		operation.setMinimumWithdrawalCoinPrice(operationEntity.getMinimumWithdrawalCoinPrice());
		operation.setOperationCtv(operationEntity.getOperationCtv());
		operation.setCoinPrice(operationEntity.getCoinPrice());
		operation.setOperationType(OperationType.valueOf(operationEntity.getOperationType()));

		return operation;
	}
}
