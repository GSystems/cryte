package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
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

	@InjectMocks
	private DepositServiceImpl depositServiceMock;

	private Client client;
	private Map<String, GenericDeposit> depositMap;
	private List<BuySellOperation> rawBuySellOperations;
	private List<BuySellOperation> processedBuySellOperations;

	@Before
	public void prepareData() {
		rawBuySellOperations = generateBuySellOperations();
		processedBuySellOperations = generateProcessedBuySellOperations();
		depositMap = generateDeposits();
		client = generateClient();
	}

	@Test
	public void prepareBuySellOperationForSaveTest() {
		List<BuySellOperation> expectedBuySellOperations = generateExpectedBuySellOperationsForSave();

		assertEquals(expectedBuySellOperations.get(0), clientServiceMock.calculatePricesAndFee(rawBuySellOperations.get(0)));
		assertEquals(expectedBuySellOperations.get(1), clientServiceMock.calculatePricesAndFee(rawBuySellOperations.get(1)));
	}

	@Test
	public void updateCryptoDepositForBuyTest() {
		CryptoDeposit expectedDeposit = (CryptoDeposit) depositMap.get(CoinType.ETHEREUM.getCode());

		Map<Double, Double> pricePerCryptoQty = new HashMap<>();
		pricePerCryptoQty.put(1.0, 75.0);

		expectedDeposit.setPricePerCryptoQty(pricePerCryptoQty);

		GenericDeposit actualDeposit = depositServiceMock.updateDeposit(client, processedBuySellOperations.get(2));

		assertEquals(expectedDeposit, actualDeposit);
	}

	@Test
	public void calculateProfitForBuyTest() {
		Double expectedValue = 0.0;
		Double actualValue = clientServiceMock.calculateProfit(client, processedBuySellOperations.get(0));

		assertEquals(expectedValue, actualValue, 0);
	}

	@Test
	public void calculateProfitForSellTest() {
		Double expectedValue = 0.0;
		Double actualValue = clientServiceMock.calculateProfit(client, processedBuySellOperations.get(3));

		assertEquals(expectedValue, actualValue, 0);
	}

	@Test
	public void calculateTotalFeeCtvTest() {
		Double expectedValue = 0.54;
		Double actualValue = clientServiceMock.calculateTotalFeeCtv(client, processedBuySellOperations.get(0));

		assertEquals(expectedValue, actualValue);
	}

	private Map<String, GenericDeposit> generateDeposits() {
		Map<String, GenericDeposit> depositMap = new HashMap<>();

		CurrencyDeposit currencyDeposit0 = new CurrencyDeposit();

		currencyDeposit0.setCoinId(CoinType.EURO.getCode());
		currencyDeposit0.setCoinName(CoinType.EURO.name());
		currencyDeposit0.setId(1);
		currencyDeposit0.setBalance(0.0);

		depositMap.put(currencyDeposit0.getCoinId(), currencyDeposit0);

		CryptoDeposit cryptoDeposit1 = new CryptoDeposit();

		cryptoDeposit1.setCoinId(CoinType.ETHEREUM.getCode());
		cryptoDeposit1.setCoinName(CoinType.ETHEREUM.name());
		cryptoDeposit1.setId(2);
		cryptoDeposit1.setPricePerCryptoQty(new HashMap<>());

		depositMap.put(cryptoDeposit1.getCoinId(), cryptoDeposit1);

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
		client.setBuySellOperations(rawBuySellOperations);
		client.setDeposits(depositMap);

		return client;
	}

	private List<BuySellOperation> generateExpectedBuySellOperationsForSave() {
		List<BuySellOperation> expectedBuySellOperations = rawBuySellOperations;

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
		BuySellOperation buySellOperation2 = new BuySellOperation();
		BuySellOperation buySellOperation3 = new BuySellOperation();

		buySellOperation0.setCoinId("ETH");
		buySellOperation0.setCoinQty(0.65);
		buySellOperation0.setDate(LocalDate.now());
		buySellOperation0.setId(1L);
		buySellOperation0.setOperationCtv(208.95);
		buySellOperation0.setOperationType(OperationType.BUY);

		buySellOperation1.setCoinId("XBT");
		buySellOperation1.setCoinQty(0.035);
		buySellOperation1.setDate(LocalDate.now());
		buySellOperation1.setId(2L);
		buySellOperation1.setOperationCtv(199.88);
		buySellOperation1.setOperationType(OperationType.BUY);

		buySellOperation2.setCoinId("ETH");
		buySellOperation2.setCoinQty(1.0);
		buySellOperation2.setDate(LocalDate.now());
		buySellOperation2.setId(3L);
		buySellOperation2.setOperationCtv(50.00);
		buySellOperation2.setOperationType(OperationType.BUY);

		buySellOperation3.setCoinId("ETH");
		buySellOperation3.setCoinQty(1.0);
		buySellOperation3.setDate(LocalDate.now());
		buySellOperation3.setId(3L);
		buySellOperation3.setOperationCtv(75.00);
		buySellOperation3.setOperationType(OperationType.SELL);

		buySellOperations.add(buySellOperation0);
		buySellOperations.add(buySellOperation1);
		buySellOperations.add(buySellOperation2);
		buySellOperations.add(buySellOperation3);

		return buySellOperations;
	}

	private List<BuySellOperation> generateProcessedBuySellOperations() {
		List<BuySellOperation> operations = new ArrayList<>();

		for (BuySellOperation operation : rawBuySellOperations) {
			operations.add(clientServiceMock.calculatePricesAndFee(operation));
		}

		return operations;
	}
}
