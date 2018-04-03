package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Operation;
import com.eu.gsys.infrastructure.entities.OperationEntity;

import java.util.ArrayList;
import java.util.List;

public class OperationTransformer {

	public static OperationEntity fromOperationToEntity(Operation operation) {
		OperationEntity operationEntity = new OperationEntity();

		operationEntity.setId(operation.getId());
		operationEntity.setDate(operation.getDate());
		operationEntity.setCoinQty(operation.getCoinQty());
		operationEntity.setCoinName(operation.getCoinName());
		operationEntity.setCoinId(operation.getCoinId());
		operationEntity.setBuy(operation.getBuy());
		operationEntity.setFeePerc(operation.getFeePerc());
		operationEntity.setFeeCtv(operation.getFeeCtv());
		operationEntity.setMinimumWithdrawalCoinPrice(operation.getMinimumWithdrawalCoinPrice());
		operationEntity.setOperationCtv(operation.getOperationCtv());
		operationEntity.setCoinPrice(operation.getCoinPrice());
		operationEntity.setSell(operation.getSell());

		return operationEntity;
	}

	public static Iterable<Operation> toOperationListFromEntity(Iterable<OperationEntity> operationEntities) {
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
		operation.setCoinName(operationEntity.getCoinName());
		operation.setCoinId(operationEntity.getCoinId());
		operation.setBuy(operationEntity.getBuy());
		operation.setFeePerc(operationEntity.getFeePerc());
		operation.setFeeCtv(operationEntity.getFeeCtv());
		operation.setMinimumWithdrawalCoinPrice(operationEntity.getMinimumWithdrawalCoinPrice());
		operation.setOperationCtv(operationEntity.getOperationCtv());
		operation.setCoinPrice(operationEntity.getCoinPrice());
		operation.setSell(operationEntity.getSell());

		return operation;
	}
}
