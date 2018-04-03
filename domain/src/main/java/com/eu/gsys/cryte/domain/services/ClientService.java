package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.models.Client;

public interface ClientService {

	Client saveClient(Client client);
	Iterable<Client> listAllClients();
	Client getClientById(Integer id);
	void deleteClient(Integer id);

	void addOperation(Client client, BuySellOperation buySellOperation);
}
