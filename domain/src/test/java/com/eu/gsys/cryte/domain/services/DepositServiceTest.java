package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class DepositServiceTest {

    @InjectMocks
    private DepositServiceImpl depositServiceMock;

    private Map<CoinType, GenericDeposit> depositMap;
    private Client client;
    private List<Operation> operations;

    @Before
    public void prepareData() {
        depositMap = generateDeposits();
        operations = generateBuySellOperations();
        client = generateClient();
    }

    @Test
    public void updateCryptoDepositForBuyTest() {
        CryptoDeposit expectedDeposit1 = new CryptoDeposit();
        CryptoDeposit expectedDeposit2 = new CryptoDeposit();

        Pair<Double, Double> cryptoQtyAtPrice1 = Pair.of(0.5, 200.0);
        expectedDeposit1.setCoinQtyAndTotalOpCtv(cryptoQtyAtPrice1);
        expectedDeposit1.setCoinType(CoinType.ETHEREUM);
        expectedDeposit1.setId(2);
        expectedDeposit1.setProfitCtv(-200.0);
        expectedDeposit1.setPricePerCoin(400.0);

        Pair<Double, Double> cryptoQtyAtPrice2 = Pair.of(2.5, 1200.0);
        expectedDeposit2.setCoinQtyAndTotalOpCtv(cryptoQtyAtPrice2);
        expectedDeposit2.setCoinType(CoinType.ETHEREUM);
        expectedDeposit2.setId(2);
        expectedDeposit2.setProfitCtv(-1200.0);
        expectedDeposit2.setPricePerCoin(480.0);

        GenericDeposit actualDeposit = depositServiceMock.updateDeposit(client, operations.get(0));
        assertEquals(expectedDeposit1, actualDeposit);

        actualDeposit = depositServiceMock.updateDeposit(client, operations.get(1));
        assertEquals(expectedDeposit2, actualDeposit);
    }

    @Test
    public void updateCryptoDepositForSellTest() {
        CryptoDeposit expectedDeposit1 = new CryptoDeposit();

        expectedDeposit1.setCoinType(CoinType.ETHEREUM);
        expectedDeposit1.setId(2);

        Pair<Double, Double> cryptoQtyAtPrice = Pair.of(1.0, 0.0);
        expectedDeposit1.setCoinQtyAndTotalOpCtv(cryptoQtyAtPrice);
        expectedDeposit1.setProfitCtv(300.0);
        expectedDeposit1.setPricePerCoin(0.0);

        depositServiceMock.updateDeposit(client, operations.get(0));
        depositServiceMock.updateDeposit(client, operations.get(1));

        GenericDeposit actualDeposit = depositServiceMock.updateDeposit(client, operations.get(2));

        assertEquals(expectedDeposit1, actualDeposit);
    }


    private Client generateClient() {
        Client client = new Client();

        client.setId(1);
        client.setFirstname("Raul");
        client.setLastname("Mendez");
        client.setEmail("raulmendez@email.com");
        client.setProfitCtv(0.0);
        client.setPayedFeesCtv(0.0);
        client.setOperations(operations);
        client.setDeposits(depositMap);

        return client;
    }

    private Map<CoinType, GenericDeposit> generateDeposits() {
        Map<CoinType, GenericDeposit> depositMap = new HashMap<>();

        CurrencyDeposit currencyDeposit0 = new CurrencyDeposit();

        currencyDeposit0.setCoinType(CoinType.EURO);
        currencyDeposit0.setId(1);
        currencyDeposit0.setBalance(0.0);

        depositMap.put(currencyDeposit0.getCoinType(), currencyDeposit0);

        CryptoDeposit cryptoDeposit1 = new CryptoDeposit();

        cryptoDeposit1.setCoinType(CoinType.ETHEREUM);
        cryptoDeposit1.setId(2);
        cryptoDeposit1.setPricePerCoin(0.0);
        cryptoDeposit1.setProfitCtv(0.0);

        depositMap.put(cryptoDeposit1.getCoinType(), cryptoDeposit1);

        return depositMap;
    }

    private List<Operation> generateBuySellOperations() {
        List<Operation> operations = new ArrayList<>();
        Operation operation0 = new Operation();
        Operation operation1 = new Operation();
        Operation operation2 = new Operation();
        Operation operation3 = new Operation();

        operation0.setCoinType(CoinType.ETHEREUM);
        operation0.setCoinQty(0.5);
        operation0.setDate(LocalDate.now());
        operation0.setId(1);
        operation0.setOperationCtv(200.0);
        operation0.setOperationType(OperationType.BUY);
        operation0.setFeeCtv(0.52);
        operation0.setCoinPrice(400.0);
        operation0.setMinimumWithdrawalCoinPrice(401.04);

        operation1.setCoinType(CoinType.ETHEREUM);
        operation1.setCoinQty(2.0);
        operation1.setDate(LocalDate.now());
        operation1.setId(10);
        operation1.setOperationCtv(1000.0);
        operation1.setOperationType(OperationType.BUY);
        operation1.setFeeCtv(2.6);
        operation1.setCoinPrice(500.0);
        operation1.setMinimumWithdrawalCoinPrice(505.2);

        operation2.setCoinType(CoinType.ETHEREUM);
        operation2.setCoinQty(1.5);
        operation2.setDate(LocalDate.now());
        operation2.setId(3);
        operation2.setOperationCtv(1500.0);
        operation2.setOperationType(OperationType.SELL);
        operation2.setFeeCtv(3.9);
        operation2.setCoinPrice(700.0);
        operation2.setMinimumWithdrawalCoinPrice(0.0);

        operation3.setCoinType(CoinType.ETHEREUM);
        operation3.setCoinQty(1.0);
        operation3.setDate(LocalDate.now());
        operation3.setId(4);
        operation3.setOperationCtv(75.0);
        operation3.setOperationType(OperationType.SELL);
        operation3.setFeeCtv(0.19);
        operation3.setCoinPrice(75.0);
        operation3.setMinimumWithdrawalCoinPrice(0.0);

        operations.add(operation0);
        operations.add(operation1);
        operations.add(operation2);
        operations.add(operation3);

        return operations;
    }
}
