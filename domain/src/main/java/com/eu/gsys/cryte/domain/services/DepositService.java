package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.GenericDeposit;

public interface DepositService {

	GenericDeposit saveDeposit(GenericDeposit deposit);
	Iterable<GenericDeposit> listAllDeposits();
	GenericDeposit getDepositById(Integer id);
	void deleteDeposit(Integer id);

	GenericDeposit updateDeposit(Client client, BuySellOperation buySellOperation);
}
