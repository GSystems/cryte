package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.transformers.ClientTransformer;
import com.eu.gsys.cryte.domain.util.FeePercEnum;
import com.eu.gsys.cryte.domain.util.GeneralConstants;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;

@Service
public class ClientServiceImpl implements ClientService {

	private DepositService depositService;
	private ClientRepository clientRepository;

	@Autowired
	public void setDepositService(DepositService depositService) {
		this.depositService = depositService;
	}

	@Autowired
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client saveClient(Client client) {
		return ClientTransformer.toClientFromEntity(clientRepository.save(ClientTransformer.fromClientToEntity(client)));
	}

	@Override
	public Iterable<Client> listAllClients() {
		return ClientTransformer.toClientListFromEntity(clientRepository.findAll());
	}

	@Override
	public Client getClientById(Integer id) {
		return ClientTransformer.toClientFromEntity(clientRepository.findById(id).get());
	}

	@Override
	public void deleteClient(Integer id) {
		clientRepository.deleteById(id);
	}
}
