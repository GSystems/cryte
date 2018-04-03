package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.BuySellOperation;
import com.eu.gsys.cryte.domain.models.Client;
import com.eu.gsys.cryte.domain.models.Deposit;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class BuySellOperationTest {

	@InjectMocks
	private ClientServiceImpl clientServiceMock;

	private Client client;
	private Map<CoinType, Deposit> depositMap;
	private List<BuySellOperation> buySellOperations;
	private BuySellOperation processedOperation;

	@Before
	public void prepareData() {
		buySellOperations = generateBuySellOperations();
		depositMap = generateDeposits();
		client = generateClient();
		processedOperation = clientServiceMock.calculatePricesAndFee(buySellOperations.get(0));
	}

	@Test
	public void prepareBuySellOperationForSaveTest() {
		List<BuySellOperation> expectedBuySellOperations = generateExpectedBuySellOperationsForSave();

		assertEquals(expectedBuySellOperations.get(0), clientServiceMock.calculatePricesAndFee(buySellOperations.get(0)));
		assertEquals(expectedBuySellOperations.get(1), clientServiceMock.calculatePricesAndFee(buySellOperations.get(1)));
	}

	@Test
	public void processOperationTest() {

		Client expectedClient = client;


		Client updatedClient = clientServiceMock.processOperation(client, processedOperation);
	}

	@Test
	public void calculateTotalFeeCtvTest() {
		Double expectedValue = 0.54;
		Double actualValue = clientServiceMock.calculateTotalFeeCtv(client, processedOperation);

		assertEquals(expectedValue, actualValue);
	}

	private Map<CoinType, Deposit> generateDeposits() {
		Map<CoinType, Deposit> depositMap = new HashMap<>();

		Deposit deposit0 = new Deposit();

		deposit0.setCtv(100.0);
		deposit0.setCoinId(CoinType.EURO);
		deposit0.setCoinName(CoinType.EURO.name());
		deposit0.setId(1);

		depositMap.put(deposit0.getCoinId(), deposit0);
		return depositMap;
	}

	private Client generateClient() {
		Client client = new Client();

		client.setId(1);
		client.setFirstname("Raul");
		client.setLastname("Mendez");
		client.setEmail("raulmendez@email.com");
		client.setProfitCtv(0.0);
		client.setPayedFeesCtv(0.0);
		client.setBuySellOperations(buySellOperations);
		client.setDeposits(depositMap);

		return client;
	}

	private List<BuySellOperation> generateExpectedBuySellOperationsForSave() {
		List<BuySellOperation> expectedBuySellOperations = buySellOperations;

		expectedBuySellOperations.get(0).setFeeCtv(0.54);
		expectedBuySellOperations.get(0).setCoinPrice(321.46);
		expectedBuySellOperations.get(0).setMinimumWithdrawalCoinPrice(323.13);

		expectedBuySellOperations.get(1).setFeeCtv(0.51);
		expectedBuySellOperations.get(1).setCoinPrice(5710.86);
		expectedBuySellOperations.get(1).setMinimumWithdrawalCoinPrice(5739.99);

		return expectedBuySellOperations;
	}

	private List<BuySellOperation> generateBuySellOperations() {
		List<BuySellOperation> buySellOperations = new ArrayList<>();
		BuySellOperation buySellOperation0 = new BuySellOperation();
		BuySellOperation buySellOperation1 = new BuySellOperation();

		buySellOperation0.setCoinId("ETH");
		buySellOperation0.setCoinQty(0.65);
		buySellOperation0.setDate(LocalDate.now());
		buySellOperation0.setId(1L);
		buySellOperation0.setOperationCtv(208.95);
		buySellOperation0.setOperationType(OperationEnum.BUY);

		buySellOperation1.setCoinId("XBT");
		buySellOperation1.setCoinQty(0.035);
		buySellOperation1.setDate(LocalDate.now());
		buySellOperation1.setId(2L);
		buySellOperation1.setOperationCtv(199.88);
		buySellOperation1.setOperationType(OperationEnum.BUY);

		buySellOperations.add(buySellOperation0);
		buySellOperations.add(buySellOperation1);

		return buySellOperations;
	}
}
