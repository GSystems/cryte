package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.infrastructure.entities.GenericDeposit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositTransformer {

	public static Map<CoinType, com.eu.gsys.cryte.domain.models.GenericDeposit> fromDepositListToMap(List<GenericDeposit> deposits) {
		Map<CoinType, com.eu.gsys.cryte.domain.models.GenericDeposit> depositMap = new HashMap<>();

		for (GenericDeposit depositEntity : deposits) {
			depositMap.put(CoinType.valueOf(depositEntity.getCoinId()), toDepositFromEntity(depositEntity));
		}

		return depositMap;
	}

	public static List<com.eu.gsys.cryte.domain.models.GenericDeposit> toDepositListFromEntity(Iterable<GenericDeposit> depositEntities) {
		List<com.eu.gsys.cryte.domain.models.GenericDeposit> cryptoDeposits = new ArrayList<>();

		for (GenericDeposit entity : depositEntities) {
			cryptoDeposits.add(toDepositFromEntity(entity));
		}

		return cryptoDeposits;
	}

	public static com.eu.gsys.cryte.domain.models.GenericDeposit toDepositFromEntity(GenericDeposit entity) {
		com.eu.gsys.cryte.domain.models.GenericDeposit genericDeposit = new com.eu.gsys.cryte.domain.models.GenericDeposit();

		return genericDeposit;
	}

	public static GenericDeposit fromDepositToEntity(com.eu.gsys.cryte.domain.models.GenericDeposit genericDeposit) {
		GenericDeposit depositEntity = new GenericDeposit();

		return depositEntity;
	}
}
