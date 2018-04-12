package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.GenericDeposit;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.infrastructure.entities.ClientEntity;
import com.eu.gsys.cryte.infrastructure.entities.CryptoDepositEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eu.gsys.cryte.domain.transformers.OperationTransformer.fromOperationListToEntity;

public class ClientTransformer {

	public static List<Client> toClientListFromEntity(Iterable<ClientEntity> clientEntities) {
		List<Client> clients = new ArrayList<>();

		for (ClientEntity entity : clientEntities) {
			clients.add(toClientFromEntity(entity));
		}

		return clients;
	}

	public static Client toClientFromEntity(ClientEntity clientEntity) {
		Client client = new Client();

		Map<CoinType, GenericDeposit> deposits = new HashMap<>();

		for (CryptoDepositEntity cryptoDepositEntity : clientEntity.getCryptoDeposits()) {
			deposits.put(
					CoinType.valueOf(cryptoDepositEntity.getCoinName()),
					DepositTransformer.toDepositFromEntity(cryptoDepositEntity));
		}

		client.setDeposits(deposits);

		client.setEmail(clientEntity.getEmail());
		client.setFirstname(clientEntity.getFirstname());
		client.setId(clientEntity.getId());
		client.setLastname(clientEntity.getLastname());
		client.setOperations(OperationTransformer.toOperationListFromEntity(clientEntity.getOperations()));
		client.setPayedFeesCtv(clientEntity.getPayedFeesCtv());
		client.setProfitCtv(clientEntity.getProfitCtv());

		return client;
	}

	public static ClientEntity fromClientToEntity(Client client) {
		ClientEntity clientEntity = new ClientEntity();

		List<GenericDeposit> deposits = new ArrayList<>(client.getDeposits().values());

		clientEntity.setCryptoDeposits(DepositTransformer.fromGenericListToCryptoDepositEntity(deposits));
		clientEntity.setCurrencyDeposits(DepositTransformer.fromGenericListToCurrencyDepositEntity(deposits));
		clientEntity.setEmail(client.getEmail());
		clientEntity.setFirstname(client.getFirstname());
		clientEntity.setId(client.getId());
		clientEntity.setLastname(client.getLastname());
		clientEntity.setOperations(fromOperationListToEntity(client.getOperations()));
		clientEntity.setPayedFeesCtv(client.getPayedFeesCtv());
		clientEntity.setProfitCtv(client.getProfitCtv());

		return clientEntity;
	}


}
