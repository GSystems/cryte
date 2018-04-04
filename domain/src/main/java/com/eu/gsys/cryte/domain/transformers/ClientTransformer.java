package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.infrastructure.entities.ClientEntity;

import java.util.ArrayList;
import java.util.List;

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
		client.setOperations(OperationTransformer.toOperationListFromEntity(clientEntity.getOperations()));
		client.setPayedFeesCtv(clientEntity.getPayedFeesCtv());
		client.setProfitCtv(clientEntity.getProfitCtv());

		return client;
	}

	public static ClientEntity fromClientToEntity(Client client) {
		ClientEntity clientEntity = new ClientEntity();

		return clientEntity;
	}
}
