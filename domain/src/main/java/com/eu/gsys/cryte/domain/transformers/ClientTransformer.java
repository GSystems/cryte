package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.CryptoDeposit;
import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.models.GenericDeposit;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.entities.ClientEntity;
import com.eu.gsys.infrastructure.entities.DepositEntity;
import com.eu.gsys.infrastructure.entities.BuySellOperationEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		client.setDeposits(DepositTransformer.fromDepositListToMap(clientEntity.getDeposits()));
		client.setEmail(clientEntity.getEmail());
		client.setFirstname(clientEntity.getFirstname());
		client.setId(clientEntity.getId());
		client.setLastname(clientEntity.getLastname());
		client.setBuySellOperations(OperationTransformer.toBuySellOperationListFromEntity(clientEntity.getOperations()));
		client.setPayedFeesCtv(clientEntity.getPayedFeesCtv());
		client.setProfitCtv(clientEntity.getProfitCtv());

		return client;
	}

	public static ClientEntity fromClientToEntity(Client client) {
		ClientEntity clientEntity = new ClientEntity();

		return clientEntity;
	}
}
