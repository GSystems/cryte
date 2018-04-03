package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.Deposit;

public interface DepositService {

	Deposit saveDeposit(Deposit deposit);
	Iterable<Deposit> listAllDeposits();
	Deposit getDepositById(Integer id);
	void deleteDeposit(Integer id);
}
