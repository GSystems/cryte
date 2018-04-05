package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.transformers.DepositTransformer;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl implements DepositService {

	private DepositRepository depositRepository;

	@Autowired
	public void setDepositRepository(DepositRepository depositRepository) {
		this.depositRepository = depositRepository;
	}

	@Override
	public GenericDeposit saveDeposit(GenericDeposit deposit) {
		return DepositTransformer
				.toDepositFromEntity(depositRepository.save(DepositTransformer.fromDepositToEntity(deposit)));
	}

	@Override
	public Iterable<GenericDeposit> listAllDeposits() {
		return DepositTransformer.toDepositListFromEntity(depositRepository.findAll());
	}

	@Override
	public GenericDeposit getDepositById(Integer id) {
		return DepositTransformer.toDepositFromEntity(depositRepository.findById(id).get());
	}

	@Override
	public void deleteDeposit(Integer id) {
		depositRepository.deleteById(id);
	}

	@Override
	public GenericDeposit updateDeposit(Client client, Operation operation) {

		GenericDeposit deposit;

		if (CoinType.EURO.getCode().equals(operation.getCoinType())) {
			deposit = updateCurrencyDeposit(client, operation);
		} else {
			deposit = updateCryptoDeposit(client, operation);
		}

		client.getDeposits().replace(operation.getCoinType(), deposit);

		return deposit;
	}

	private CurrencyDeposit updateCurrencyDeposit(Client client, Operation operation) {
		CurrencyDeposit deposit = (CurrencyDeposit) client.getDeposits().get(operation.getCoinType());


		return deposit;
	}

	private CryptoDeposit updateCryptoDeposit(Client client, Operation operation) {

		Pair<Double, Double> coinQtyAndTotalOpCtv;
		Pair<Double, Double> oldCryptoQtyAtPrice = Pair.of(0.0, 0.0);
		CryptoDeposit deposit = (CryptoDeposit) client.getDeposits().get(operation.getCoinType());

		if (deposit.getCoinQtyAndTotalOpCtv() != null) {
			oldCryptoQtyAtPrice = deposit.getCoinQtyAndTotalOpCtv();
		}

		coinQtyAndTotalOpCtv = calculateQtyAndPriceForSave(oldCryptoQtyAtPrice, operation);

		if (coinQtyAndTotalOpCtv.getSecond() < 0) {
			coinQtyAndTotalOpCtv = Pair.of(coinQtyAndTotalOpCtv.getFirst(), 0.0);
		}

		deposit.setCoinQtyAndTotalOpCtv(coinQtyAndTotalOpCtv);

		Pair<Double, Double> profitAndNewPricePerCoin = calculateProfitAndNewPricePerCoin(operation, deposit);

		deposit.setProfitCtv(profitAndNewPricePerCoin.getFirst());
		deposit.setPricePerCoin(profitAndNewPricePerCoin.getSecond());

		return deposit;
	}

	private Pair<Double, Double> calculateQtyAndPriceForSave(Pair<Double, Double> coinQtyAndTotalOpCtv, Operation operation) {
		Pair<Double, Double> newCoinQtyAndTotalOpCtv;

		Double qtyForSave = 0.0;
		Double oldQty = coinQtyAndTotalOpCtv.getFirst();
		Double newQty = operation.getCoinQty();

		Double oldTotalOperationsCtv = coinQtyAndTotalOpCtv.getSecond();
		Double newPrice = operation.getOperationCtv();
		Double totalOperationsCtvForSave = 0.0;

		if (OperationType.BUY.equals(operation.getOperationType())) {
			qtyForSave = oldQty + newQty;
			totalOperationsCtvForSave = oldTotalOperationsCtv + newPrice;
		} else if (OperationType.SELL.equals(operation.getOperationType())) {
			qtyForSave = oldQty - newQty;
			totalOperationsCtvForSave = oldTotalOperationsCtv - newPrice;
		}

		newCoinQtyAndTotalOpCtv = Pair.of(qtyForSave, totalOperationsCtvForSave);

		return newCoinQtyAndTotalOpCtv;
	}

	private Pair<Double, Double> calculateProfitAndNewPricePerCoin(Operation operation, CryptoDeposit deposit) {

		Double newProfit = 0.0;
		Double newPricePerCoin;
		Pair<Double, Double> profitAndPricePerCoin;

		Double oldProfit = deposit.getProfitCtv();
		Double coinQty = deposit.getCoinQtyAndTotalOpCtv().getFirst();
		Double totalOpCtv = deposit.getCoinQtyAndTotalOpCtv().getSecond();

		if (OperationType.BUY.equals(operation.getOperationType())) {
			newProfit = oldProfit - operation.getOperationCtv();
		} else if (OperationType.SELL.equals(operation.getOperationType())) {
			newProfit = oldProfit + operation.getOperationCtv();
		}

		if (totalOpCtv == 0) {
			newPricePerCoin = 0.0;
		} else {
			newPricePerCoin = totalOpCtv / coinQty;
		}

		profitAndPricePerCoin = Pair.of(newProfit, newPricePerCoin);

		return profitAndPricePerCoin;
	}
}
