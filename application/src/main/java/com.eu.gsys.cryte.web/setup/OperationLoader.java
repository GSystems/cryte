package com.eu.gsys.cryte.web.setup;

import com.eu.gsys.cryte.domain.services.OperationService;
import com.eu.gsys.cryte.web.models.ClientModel;
import com.eu.gsys.cryte.web.models.DepositModel;
import com.eu.gsys.cryte.web.models.OperationModel;
import com.eu.gsys.cryte.web.util.CoinEnum;
import com.eu.gsys.cryte.web.util.OperationEnum;
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
public class OperationLoader implements ApplicationListener<ContextRefreshedEvent> {

	private OperationService clientService;
	private Logger log = Logger.getLogger(OperationLoader.class);

	@Autowired
	public void setClientService(OperationService clientService) {
		this.clientService = clientService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ClientModel client = new ClientModel();

		client.setId(1);
		client.setEmail("email@email.com");
		client.setFirstname("Firstname");
		client.setLastname("Lastname");
		client.setPayedFeesCtv(0.0);
		client.setProfitCtv(0.0);
		client.setDeposits(generateDeposits());
		client.setOperations(generateOperations());

	}

	private Map<String, DepositModel> generateDeposits() {
		Map<String, DepositModel> deposits = new HashMap<>();

		DepositModel eurDeposit = new DepositModel();
		eurDeposit.setCoinId(CoinEnum.EURO.getCode());
		eurDeposit.setCoinName(CoinEnum.EURO.name());
		eurDeposit.setCtv(100.0);

		deposits.put(eurDeposit.getCoinId(), eurDeposit);

		return deposits;
	}

	private List<OperationModel> generateOperations() {
		List<OperationModel> operations = new ArrayList<>();

		OperationModel first = new OperationModel();

		first.setId(1L);
		first.setCoinId(CoinEnum.BITCOIN.getCode());
		first.setCoinQty(1.0);
		first.setOperationCtv(5700.0);
		first.setCoinPrice(5700.0);
		first.setDate(LocalDate.now());
		first.setOperationType(OperationEnum.BUY.getCode());

		OperationModel second = new OperationModel();

		second.setId(2L);
		second.setCoinId(CoinEnum.ETHEREUM.getCode());
		second.setCoinQty(2.0);
		second.setOperationCtv(600.0);
		second.setCoinPrice(1200.0);
		second.setDate(LocalDate.now());
		second.setOperationType(OperationEnum.BUY.getCode());

		operations.add(first);
		operations.add(second);

		return operations;
	}
}

