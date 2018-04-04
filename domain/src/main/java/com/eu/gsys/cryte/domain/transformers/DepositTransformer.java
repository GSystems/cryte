package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.CryptoDeposit;
import com.eu.gsys.cryte.domain.models.GenericDeposit;
import com.eu.gsys.infrastructure.entities.DepositEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositTransformer {

	public static Map<String, GenericDeposit> fromDepositListToMap(List<DepositEntity> deposits) {
		Map<String, GenericDeposit> depositMap = new HashMap<>();

		for (DepositEntity depositEntity : deposits) {
			depositMap.put(depositEntity.getCoinId(), toDepositFromEntity(depositEntity));
		}

		return depositMap;
	}

	public static List<GenericDeposit> toDepositListFromEntity(Iterable<DepositEntity> depositEntities) {
		List<GenericDeposit> cryptoDeposits = new ArrayList<>();

		for (DepositEntity entity : depositEntities) {
			cryptoDeposits.add(toDepositFromEntity(entity));
		}

		return cryptoDeposits;
	}

	public static GenericDeposit toDepositFromEntity(DepositEntity entity) {
		GenericDeposit genericDeposit = new GenericDeposit();

		return genericDeposit;
	}

	public static DepositEntity fromDepositToEntity(GenericDeposit genericDeposit) {
		DepositEntity depositEntity = new DepositEntity();

		return depositEntity;
	}
}
