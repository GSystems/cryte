package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.entities.BuySellOperationEntity;

import java.util.ArrayList;
import java.util.List;

public class OperationTransformer {

	public static BuySellOperationEntity fromBuySellOperationToEntity(BuySellOperation buySellOperation) {
		BuySellOperationEntity buySellOperationEntity = new BuySellOperationEntity();

		buySellOperationEntity.setId(buySellOperation.getId());
		buySellOperationEntity.setDate(buySellOperation.getDate());
		buySellOperationEntity.setCoinQty(buySellOperation.getCoinQty());
		buySellOperationEntity.setCoinName(buySellOperation.getCoinName());
		buySellOperationEntity.setCoinId(buySellOperation.getCoinId());
		buySellOperationEntity.setFeePerc(buySellOperation.getFeePerc());
		buySellOperationEntity.setFeeCtv(buySellOperation.getFeeCtv());
		buySellOperationEntity.setMinimumWithdrawalCoinPrice(buySellOperation.getMinimumWithdrawalCoinPrice());
		buySellOperationEntity.setOperationCtv(buySellOperation.getOperationCtv());
		buySellOperationEntity.setCoinPrice(buySellOperation.getCoinPrice());
		buySellOperationEntity.setOperationType(buySellOperation.getOperationType().getCode());

		return buySellOperationEntity;
	}

	public static List<BuySellOperation> toBuySellOperationListFromEntity(
			Iterable<BuySellOperationEntity> operationEntities) {
		List<BuySellOperation> buySellOperationList = new ArrayList<>();

		for (BuySellOperationEntity entity : operationEntities) {
			buySellOperationList.add(toBuySellOperationFromEntity(entity));
		}

		return buySellOperationList;
	}

	public static BuySellOperation toBuySellOperationFromEntity(BuySellOperationEntity buySellOperationEntity) {
		BuySellOperation buySellOperation = new BuySellOperation();

		buySellOperation.setId(buySellOperationEntity.getId());
		buySellOperation.setDate(buySellOperationEntity.getDate());
		buySellOperation.setCoinQty(buySellOperationEntity.getCoinQty());
		buySellOperation.setCoinName(buySellOperationEntity.getCoinName());
		buySellOperation.setCoinId(buySellOperationEntity.getCoinId());
		buySellOperation.setFeePerc(buySellOperationEntity.getFeePerc());
		buySellOperation.setFeeCtv(buySellOperationEntity.getFeeCtv());
		buySellOperation.setMinimumWithdrawalCoinPrice(buySellOperationEntity.getMinimumWithdrawalCoinPrice());
		buySellOperation.setOperationCtv(buySellOperationEntity.getOperationCtv());
		buySellOperation.setCoinPrice(buySellOperationEntity.getCoinPrice());
		buySellOperation.setOperationType(OperationType.valueOf(buySellOperationEntity.getOperationType()));

		return buySellOperation;
	}
}
