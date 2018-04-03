package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.transformers.CryteTransformer;
import com.eu.gsys.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client saveClient(Client client) {
		return CryteTransformer.toClientFromEntity(clientRepository.save(CryteTransformer.fromClientToEntity(client)));
	}

	@Override
	public Iterable<Client> listAllClients() {
		return CryteTransformer.toClientListFromEntity(clientRepository.findAll());
	}

	@Override
	public Client getClientById(Integer id) {
		return CryteTransformer.toClientFromEntity(clientRepository.findById(id).get());
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
}
