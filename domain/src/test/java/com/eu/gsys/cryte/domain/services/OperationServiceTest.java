package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Operation;
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

	private List<Operation> operations;

	@Before
	public void prepareData() {
		operations = generateOperations();
	}

	@Test
	public void prepareOperationForSaveTest() {
		List<Operation> expectedOperations = generateExpectedOperationsForSave();

		assertEquals(expectedOperations.get(0), operationServiceMock.prepareOperationForSave(operations.get(0)));
		assertEquals(expectedOperations.get(1), operationServiceMock.prepareOperationForSave(operations.get(1)));
	}

	private List<Operation> generateExpectedOperationsForSave() {
		List<Operation> operations = generateOperations();

		operations.get(0).setFeeCtv(0.54);
		operations.get(0).setCoinPrice(321.46);
		operations.get(0).setMinimumWithdrawalCoinPrice(323.13);

		operations.get(1).setFeeCtv(0.51);
		operations.get(1).setCoinPrice(5710.86);
		operations.get(1).setMinimumWithdrawalCoinPrice(5739.99);

		return operations;
	}

	private List<Operation> generateOperations() {
		List<Operation> operations = new ArrayList<>();
		Operation operation0 = new Operation();
		Operation operation1 = new Operation();

		operation0.setBuy(true);
		operation0.setCoinId("ETH");
		operation0.setCoinQty(0.65);
		operation0.setDate(LocalDate.now());
		operation0.setId(1L);
		operation0.setOperationCtv(208.95);

		operation1.setBuy(true);
		operation1.setCoinId("XBT");
		operation1.setCoinQty(0.035);
		operation1.setDate(LocalDate.now());
		operation1.setId(2L);
		operation1.setOperationCtv(199.88);

		operations.add(operation0);
		operations.add(operation1);

		return operations;
	}
}
