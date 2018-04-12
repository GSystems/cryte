package com.eu.gsys.cryte.web.setup;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.services.ClientService;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClientLoader implements ApplicationListener<ContextRefreshedEvent> {

	public ClientService clientService;
	private Logger log = Logger.getLogger(ClientLoader.class);

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Client client = new Client();

		client.setEmail("firstname@email.com");
		client.setFirstname("Firstname");
		client.setLastname("Lastname");
		client.setPayedFeesCtv(0.0);
		client.setProfitCtv(0.0);
		client.setDeposits(generateDeposits());
		client.setOperations(generateBuySellOperations());

		clientService.saveClient(client);
	}

	private Map<CoinType, GenericDeposit> generateDeposits() {
		Map<CoinType, GenericDeposit> deposits = new HashMap<>();

		CurrencyDeposit eurCryptoDeposit = new CurrencyDeposit();
		eurCryptoDeposit.setCoinType(CoinType.EURO);
		eurCryptoDeposit.setBalance(100.0);

		deposits.put(CoinType.EURO, eurCryptoDeposit);

		return deposits;
	}

	private List<Operation> generateBuySellOperations() {
		List<Operation> operations = new ArrayList<>();

		Operation first = new Operation();

		first.setCoinType(CoinType.BITCOIN);
		first.setCoinQty(1.0);
		first.setOperationCtv(5700.0);
		first.setCoinPrice(5700.0);
		first.setDate(LocalDate.now());
		first.setOperationType(OperationType.BUY);

		Operation second = new Operation();

		second.setCoinType(CoinType.ETHEREUM);
		second.setCoinQty(2.0);
		second.setOperationCtv(600.0);
		second.setCoinPrice(1200.0);
		second.setDate(LocalDate.now());
		second.setOperationType(OperationType.BUY);

		Operation third = new Operation();

		third.setCoinType(CoinType.EURO);
		third.setCoinQty(100.0);
		third.setDate(LocalDate.now());
		third.setOperationType(OperationType.FUND);
		third.setOperationCtv(100.0);

		operations.add(first);
		operations.add(second);
//		operations.add(third);

		return operations;
	}
}

