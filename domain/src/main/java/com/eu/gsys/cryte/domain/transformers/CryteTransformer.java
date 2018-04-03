package com.eu.gsys.cryte.domain.transformers;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.Deposit;
import com.eu.gsys.infrastructure.entities.ClientEntity;
import com.eu.gsys.infrastructure.entities.DepositEntity;

import java.util.ArrayList;
import java.util.List;

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

		return client;
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
}
