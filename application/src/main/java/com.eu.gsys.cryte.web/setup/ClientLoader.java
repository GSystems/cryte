package com.eu.gsys.cryte.web.setup;

import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.Deposit;
import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.services.ClientService;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationEnum;
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

		client.setId(1);
		client.setEmail("firstname@email.com");
		client.setFirstname("Firstname");
		client.setLastname("Lastname");
		client.setPayedFeesCtv(0.0);
		client.setProfitCtv(0.0);
		client.setDeposits(generateDeposits());
		client.setBuySellOperations(generateBuySellOperations());

		clientService.saveClient(client);
	}

	private Map<CoinType, Deposit> generateDeposits() {
		Map<CoinType, Deposit> deposits = new HashMap<>();

		Deposit eurDeposit = new Deposit();
		eurDeposit.setCoinId(CoinType.EURO);
		eurDeposit.setCoinName(CoinType.EURO.name());
		eurDeposit.setCtv(100.0);

		deposits.put(CoinType.EURO, eurDeposit);

		return deposits;
	}

	private List<BuySellOperation> generateBuySellOperations() {
		List<BuySellOperation> buySellOperations = new ArrayList<>();

		BuySellOperation first = new BuySellOperation();

		first.setId(1L);
		first.setCoinId(CoinType.BITCOIN.getCode());
		first.setCoinQty(1.0);
		first.setOperationCtv(5700.0);
		first.setCoinPrice(5700.0);
		first.setDate(LocalDate.now());
		first.setOperationType(OperationEnum.BUY);

		BuySellOperation second = new BuySellOperation();

		second.setId(2L);
		second.setCoinId(CoinType.ETHEREUM.getCode());
		second.setCoinQty(2.0);
		second.setOperationCtv(600.0);
		second.setCoinPrice(1200.0);
		second.setDate(LocalDate.now());
		second.setOperationType(OperationEnum.BUY);

		buySellOperations.add(first);
		buySellOperations.add(second);

		return buySellOperations;
	}
}

