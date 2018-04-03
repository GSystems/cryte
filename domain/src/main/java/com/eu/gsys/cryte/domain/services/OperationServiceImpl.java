package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.Operation;
import com.eu.gsys.cryte.domain.transformers.OperationTransformer;
import com.eu.gsys.infrastructure.repositories.OperationRepository;
import com.eu.gsys.cryte.domain.util.FeePercEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;

@Service
public class OperationServiceImpl implements OperationService {

	private OperationRepository operationRepository;

	@Autowired
	public void setOperationRepository(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	@Override
	public Operation saveOperation(Operation operation) {
		Operation operationForSave = prepareOperationForSave(operation);
		return OperationTransformer
				.toOperationFromEntity(operationRepository.save(OperationTransformer.fromOperationToEntity(operationForSave)));
	}

	@Override
	public Iterable<Operation> listAllOperations() {
		return OperationTransformer.toOperationListFromEntity(operationRepository.findAll());
	}

	@Override
	public Operation getOperationById(Integer id) {
		return OperationTransformer.toOperationFromEntity(operationRepository.findById(id).get());
	}

	protected Operation prepareOperationForSave(Operation operation) {
		Operation operationForSave = operation;

		Double coinPrice = operation.getOperationCtv() / operation.getCoinQty();
		Double feeCtv = operation.getOperationCtv() * FeePercEnum.valueOf(operation.getCoinId()).getFeePerc() / 100;
		Double minimumWithdrawalCoinPrice = (operation.getOperationCtv() + 2 * feeCtv) * coinPrice / operation.getOperationCtv();

		operationForSave.setCoinPrice(roundDoubleValue(coinPrice));
		operationForSave.setFeeCtv(roundDoubleValue(feeCtv));
		operationForSave.setMinimumWithdrawalCoinPrice(roundDoubleValue(minimumWithdrawalCoinPrice));

		return operationForSave;
	}

	private Double roundDoubleValue(Double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		String formate = df.format(value);

		Double finalValue = 0.0;

		try {
			finalValue = (Double) df.parse(formate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finalValue;
	}
}
