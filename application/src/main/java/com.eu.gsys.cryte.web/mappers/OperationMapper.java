package com.eu.gsys.cryte.web.mappers;

import com.eu.gsys.cryte.domain.models.Operation;
import com.eu.gsys.cryte.web.models.OperationModel;

public class OperationMapper {

	public static Operation toOperationFromModel(OperationModel operationModel) {
		Operation operation = new Operation();

		operation.setId(operationModel.getId());
		operation.setDate(operationModel.getDate());
		operation.setCoinQty(operationModel.getCoinQty());
		operation.setCoinId(operationModel.getCoinId());
		operation.setFeeCtv(operationModel.getFeeCtv());
		operation.setFeePerc(operationModel.getFeePerc());
		operation.setMinimumWithdrawalCoinPrice(operationModel.getMinimumWithdrawalCoinPrice());
		operation.setOperationCtv(operationModel.getOperationCtv());
		operation.setCoinPrice(operationModel.getCoinPrice());
		operation.setCurrency(operationModel.getCurrency());
		operation.setCurrencyPrice(operationModel.getCurrencyPrice());
		operation.setP

		return operation;
	}
}
