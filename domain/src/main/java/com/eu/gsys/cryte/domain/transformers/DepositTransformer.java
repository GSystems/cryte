package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.CryptoDeposit;
import com.eu.gsys.cryte.domain.models.CurrencyDeposit;
import com.eu.gsys.cryte.domain.models.GenericDeposit;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.infrastructure.entities.CryptoDepositEntity;
import com.eu.gsys.cryte.infrastructure.entities.CurrencyDepositEntity;
import com.eu.gsys.cryte.infrastructure.entities.GenericDepositEntity;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositTransformer {

	public static List<GenericDeposit> toDepositListFromEntity(Iterable<GenericDepositEntity> depositEntities) {
		List<GenericDeposit> genericDeposits = new ArrayList<>();

		for (GenericDepositEntity genericDepositEntity : depositEntities) {
			genericDeposits.add(toDepositFromEntity(genericDepositEntity));
		}

		return genericDeposits;
	}

	public static GenericDeposit toDepositFromEntity(GenericDepositEntity depositEntity) {
		GenericDeposit genericDeposit;

		if (depositEntity instanceof CryptoDepositEntity) {
			genericDeposit = toGenericDepositFromCryptoEntity(depositEntity);
		} else {
			genericDeposit = toGenericDepositFromCurrencyEntity(depositEntity);
		}

		return genericDeposit;
	}


	public static GenericDeposit toGenericDepositFromEntity(GenericDepositEntity entity) {
		GenericDeposit genericDeposit = new GenericDeposit();



		return genericDeposit;
	}

	public static CryptoDeposit toGenericDepositFromCryptoEntity(GenericDepositEntity genericDepositEntity) {
		CryptoDeposit cryptoDeposit = (CryptoDeposit) retrieveCommonDepositParametersFromEntity(genericDepositEntity);
		CryptoDepositEntity cryptoDepositEntity = (CryptoDepositEntity) genericDepositEntity;

		cryptoDeposit.setCoinQtyAndTotalOpCtv(
				Pair.of(cryptoDepositEntity.getCoinQty(), cryptoDepositEntity.getTotalOperationCtv()));
		cryptoDeposit.setPricePerCoin(cryptoDepositEntity.getPricePerCoin());
		cryptoDeposit.setProfitCtv(cryptoDepositEntity.getProfitCtv());

		return cryptoDeposit;
	}

	public static CurrencyDeposit toGenericDepositFromCurrencyEntity(GenericDepositEntity genericDepositEntity) {
		CurrencyDeposit currencyDeposit = (CurrencyDeposit) retrieveCommonDepositParametersFromEntity(genericDepositEntity);
		CurrencyDepositEntity currencyDepositEntity = (CurrencyDepositEntity) genericDepositEntity;

		currencyDeposit.setBalance(currencyDepositEntity.getBalance());

		return currencyDeposit;
	}

	private static GenericDeposit retrieveCommonDepositParametersFromEntity(GenericDepositEntity genericDepositEntity) {
		GenericDeposit genericDeposit = new GenericDeposit();

		genericDeposit.setCoinType(CoinType.valueOf(genericDepositEntity.getCoinName()));
		genericDeposit.setId(genericDepositEntity.getId());

		return genericDeposit;
	}

	public static List<CryptoDepositEntity> fromGenericListToCryptoDepositEntity(List<GenericDeposit> genericDeposits) {
		List<CryptoDepositEntity> cryptoDepositEntities = new ArrayList<>();

		for (GenericDeposit deposit : genericDeposits) {
			cryptoDepositEntities.add(fromGenericToCryptoDepositEntity(deposit));
		}

		return cryptoDepositEntities;
	}

	public static List<CurrencyDepositEntity> fromGenericListToCurrencyDepositEntity(List<GenericDeposit> deposits) {
		List<CurrencyDepositEntity> currencyDepositEntities = new ArrayList<>();

		for (GenericDeposit deposit : deposits) {
			currencyDepositEntities.add(fromGenericToCurrencyDepositEntity(deposit));
		}

		return currencyDepositEntities;
	}

	public static GenericDepositEntity fromGenericDepositToCryptoCurrencyEntity(GenericDeposit deposit) {
		GenericDepositEntity genericDepositEntity;

		if (deposit instanceof CryptoDeposit) {
			genericDepositEntity = fromGenericToCryptoDepositEntity(deposit);
		} else {
			genericDepositEntity = fromGenericToCurrencyDepositEntity(deposit);
		}

		return genericDepositEntity;
	}

	public static CryptoDepositEntity fromGenericToCryptoDepositEntity(GenericDeposit deposit) {
		CryptoDeposit cryptoDeposit;

		try {
			cryptoDeposit = (CryptoDeposit) deposit;
		} catch (ClassCastException e) {
			return null;
		}

		CryptoDepositEntity cryptoDepositEntity = (CryptoDepositEntity) retrieveCommonDepositParametersToEntity(deposit);

		cryptoDepositEntity.setCoinQty(cryptoDeposit.getCoinQtyAndTotalOpCtv().getFirst());
		cryptoDepositEntity.setPricePerCoin(cryptoDeposit.getCoinQtyAndTotalOpCtv().getSecond());
		cryptoDepositEntity.setProfitCtv(cryptoDeposit.getProfitCtv());

		return cryptoDepositEntity;
	}

	public static CurrencyDepositEntity fromGenericToCurrencyDepositEntity(GenericDeposit deposit) {
		CurrencyDeposit currencyDeposit;

		try {
			currencyDeposit = (CurrencyDeposit) deposit;
		} catch (ClassCastException e) {
			return null;
		}

		CurrencyDepositEntity currencyDepositEntity = (CurrencyDepositEntity) retrieveCommonDepositParametersToEntity(deposit);

		currencyDepositEntity.setBalance(currencyDeposit.getBalance());

		return currencyDepositEntity;
	}

	private static GenericDepositEntity retrieveCommonDepositParametersToEntity(GenericDeposit genericDeposit) {
		GenericDepositEntity genericDepositEntity = new GenericDepositEntity();

		genericDepositEntity.setCoinId(genericDeposit.getCoinType().getCode());
		genericDepositEntity.setCoinName(genericDeposit.getCoinType().name());

		return genericDepositEntity;
	}

	public static Map<CoinType, GenericDeposit> fromGenericDepositListToMap(
			GenericDepositEntity genericDepositEntities) {

		Map<CoinType, GenericDeposit> depositMap = new HashMap<>();



		return depositMap;
	}
}
