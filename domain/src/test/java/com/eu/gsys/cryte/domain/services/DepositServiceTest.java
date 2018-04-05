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
        CryptoDeposit expectedDeposit = new CryptoDeposit();

        expectedDeposit.setCoinType(CoinType.ETHEREUM);
        expectedDeposit.setId(2);

        Pair<Double, Double> cryptoQtyAtPrice = Pair.of(1.0, 50.0);
        expectedDeposit.setPricePerCryptoQty(cryptoQtyAtPrice);

        GenericDeposit actualDeposit = depositServiceMock.updateDeposit(client, operations.get(2));

        assertEquals(expectedDeposit, actualDeposit);
    }

    @Test
    public void updateCryptoDepositForSellTest() {
        CryptoDeposit expectedDeposit = new CryptoDeposit();

        expectedDeposit.setCoinType(CoinType.ETHEREUM);
        expectedDeposit.setId(2);

        Pair<Double, Double> cryptoQtyAtPrice = Pair.of(1.0, 50.0);
        expectedDeposit.setPricePerCryptoQty(cryptoQtyAtPrice);
        expectedDeposit.setProfitCtv();
        expectedDeposit.setPricePerCoin();

        depositServiceMock.updateDeposit(client, operations.get(2));

        GenericDeposit actualDeposit = actualDeposit = depositServiceMock.updateDeposit(client, operations.get(3));

        assertEquals(expectedDeposit, actualDeposit);
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
        operation0.setCoinQty(0.65);
        operation0.setDate(LocalDate.now());
        operation0.setId(1L);
        operation0.setOperationCtv(208.95);
        operation0.setOperationType(OperationType.BUY);
        operation0.setFeeCtv(0.54);
        operation0.setCoinPrice(321.46);
        operation0.setMinimumWithdrawalCoinPrice(323.13);

        operation1.setCoinType(CoinType.BITCOIN);
        operation1.setCoinQty(0.035);
        operation1.setDate(LocalDate.now());
        operation1.setId(2L);
        operation1.setOperationCtv(199.88);
        operation1.setOperationType(OperationType.BUY);
        operation1.setFeeCtv(0.51);
        operation1.setCoinPrice(5710.86);
        operation1.setMinimumWithdrawalCoinPrice(5739.99);

        operation2.setCoinType(CoinType.ETHEREUM);
        operation2.setCoinQty(1.0);
        operation2.setDate(LocalDate.now());
        operation2.setId(3L);
        operation2.setOperationCtv(50.00);
        operation2.setOperationType(OperationType.BUY);
        operation2.setFeeCtv(0.2);
        operation2.setCoinPrice(50.0);
        operation2.setMinimumWithdrawalCoinPrice(0.0);

        operation3.setCoinType(CoinType.ETHEREUM);
        operation3.setCoinQty(1.0);
        operation3.setDate(LocalDate.now());
        operation3.setId(3L);
        operation3.setOperationCtv(75.00);
        operation3.setOperationType(OperationType.SELL);
        operation3.setFeeCtv(0.54);
        operation3.setCoinPrice(321.46);
        operation3.setMinimumWithdrawalCoinPrice(323.13);

        operations.add(operation0);
        operations.add(operation1);
        operations.add(operation2);
        operations.add(operation3);

        return operations;
    }
}
