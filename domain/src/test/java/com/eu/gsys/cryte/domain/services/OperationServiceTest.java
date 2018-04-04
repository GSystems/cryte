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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class OperationServiceTest {

	@InjectMocks
	private OperationServiceImpl operationServiceMock;

	private List<Operation> rawBuySellOperations;

	@Before
	public void prepareData() {
		rawBuySellOperations = generateBuySellOperations();
	}

	@Test
	public void calculatePricesAndFeeTest() {

		Double op0FeeCtv = 0.54;
		Double op0CoinPrice = 321.46;
		Double op0MinimumWithdrawalCoinPrice = 323.13;

		Double op1FeeCtv = 0.51;
		Double op1CoinPrice = 5710.86;
		Double op1MinimumWithdrawalCoinPrice = 5739.99;

		Double op2FeeCtv = 0.2;
		Double op2CoinPrice = 75.0;
		Double op2MinimumWithdrawalCoinPrice = 0.0;

		Operation operation0 = operationServiceMock.calculatePricesAndFee(rawBuySellOperations.get(0));
		Operation operation1 = operationServiceMock.calculatePricesAndFee(rawBuySellOperations.get(1));
		Operation operation2 = operationServiceMock.calculatePricesAndFee(rawBuySellOperations.get(2));


		assertEquals(op0FeeCtv, operation0.getFeeCtv(), 0);
		assertEquals(op0CoinPrice, operation0.getCoinPrice(), 0);
		assertEquals(op0MinimumWithdrawalCoinPrice, operation0.getMinimumWithdrawalCoinPrice(), 0);

		assertEquals(op1FeeCtv, operation1.getFeeCtv(), 0);
		assertEquals(op1CoinPrice, operation1.getCoinPrice(), 0);
		assertEquals(op1MinimumWithdrawalCoinPrice, operation1.getMinimumWithdrawalCoinPrice(), 0);

		assertEquals(op2FeeCtv, operation2.getFeeCtv(), 0);
		assertEquals(op2CoinPrice, operation2.getCoinPrice(), 0);
		assertEquals(op2MinimumWithdrawalCoinPrice, operation2.getMinimumWithdrawalCoinPrice(), 0);
	}

	private List<Operation> generateExpectedBuySellOperationsForSave() {
		List<Operation> expectedOperations = rawBuySellOperations;

		expectedOperations.get(0).setFeeCtv(0.54);
		expectedOperations.get(0).setCoinPrice(321.46);
		expectedOperations.get(0).setMinimumWithdrawalCoinPrice(323.13);

		expectedOperations.get(1).setFeeCtv(0.51);
		expectedOperations.get(1).setCoinPrice(5710.86);
		expectedOperations.get(1).setMinimumWithdrawalCoinPrice(5739.99);

		expectedOperations.get(2).setFeeCtv(0.51);
		expectedOperations.get(2).setCoinPrice(75.0);
		expectedOperations.get(2).setMinimumWithdrawalCoinPrice(0.0);

		return expectedOperations;
	}

	private List<Operation> generateBuySellOperations() {
		List<Operation> operations = new ArrayList<>();
		Operation operation0 = new Operation();
		Operation operation1 = new Operation();
		Operation operation2 = new Operation();

		operation0.setCoinType(CoinType.ETHEREUM);
		operation0.setCoinQty(0.65);
		operation0.setDate(LocalDate.now());
		operation0.setId(1L);
		operation0.setOperationCtv(208.95);
		operation0.setOperationType(OperationType.BUY);

		operation1.setCoinType(CoinType.BITCOIN);
		operation1.setCoinQty(0.035);
		operation1.setDate(LocalDate.now());
		operation1.setId(2L);
		operation1.setOperationCtv(199.88);
		operation1.setOperationType(OperationType.BUY);

		operation2.setCoinType(CoinType.ETHEREUM);
		operation2.setCoinQty(1.0);
		operation2.setDate(LocalDate.now());
		operation2.setId(3L);
		operation2.setOperationCtv(75.00);
		operation2.setOperationType(OperationType.SELL);

		operations.add(operation0);
		operations.add(operation1);
		operations.add(operation2);

		return operations;
	}
}
