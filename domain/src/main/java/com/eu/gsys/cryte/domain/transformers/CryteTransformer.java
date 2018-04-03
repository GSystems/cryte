package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.Deposit;
import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationEnum;
import com.eu.gsys.infrastructure.entities.ClientEntity;
import com.eu.gsys.infrastructure.entities.DepositEntity;
import com.eu.gsys.infrastructure.entities.BuySellOperationEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryteTransformer {

	public static List<Client> toClientListFromEntity(Iterable<ClientEntity> clientEntities) {
		List<Client> clients = new ArrayList<>();

		for (ClientEntity entity : clientEntities) {
			clients.add(toClientFromEntity(entity));
		}

		return clients;
	}

	public static Client toClientFromEntity(ClientEntity clientEntity) {
		Client client = new Client();

		client.setDeposits(fromDepositListToMap(clientEntity.getDeposits()));
		client.setEmail(clientEntity.getEmail());
		client.setFirstname(clientEntity.getFirstname());
		client.setId(clientEntity.getId());
		client.setLastname(clientEntity.getLastname());
		client.setBuySellOperations(toBuySellOperationListFromEntity(clientEntity.getOperations()));
		client.setPayedFeesCtv(clientEntity.getPayedFeesCtv());
		client.setProfitCtv(clientEntity.getProfitCtv());

		return client;
	}

	private static Map<CoinType,Deposit> fromDepositListToMap(List<DepositEntity> deposits) {
		Map<CoinType,Deposit> depositMap = new HashMap<>();

		for (DepositEntity depositEntity : deposits) {
			depositMap.put(CoinType.valueOf(depositEntity.getCoinId()), toDepositFromEntity(depositEntity));
		}

		return depositMap;
	}

	public static ClientEntity fromClientToEntity(Client client) {
		ClientEntity clientEntity = new ClientEntity();

		return clientEntity;
	}

	public static List<Deposit> toDepositListFromEntity(Iterable<DepositEntity> depositEntities) {
		List<Deposit> deposits = new ArrayList<>();

		for (DepositEntity entity : depositEntities) {
			deposits.add(toDepositFromEntity(entity));
		}

		return deposits;
	}

	public static Deposit toDepositFromEntity(DepositEntity entity) {
		Deposit deposit = new Deposit();

		return deposit;
	}

	public static DepositEntity fromDepositToEntity(Deposit deposit) {
		DepositEntity depositEntity = new DepositEntity();

		return depositEntity;
	}

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

	public static List<BuySellOperation> toBuySellOperationListFromEntity(Iterable<BuySellOperationEntity> operationEntities) {
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
		buySellOperation.setOperationType(OperationEnum.valueOf(buySellOperationEntity.getOperationType()));

		return buySellOperation;
	}
}
