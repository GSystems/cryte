package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.transformers.DepositTransformer;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

		return deposit;
	}

	private CurrencyDeposit updateCurrencyDeposit(Client client, Operation operation) {
		CurrencyDeposit deposit = (CurrencyDeposit) client.getDeposits().get(operation.getCoinType());


		return deposit;
	}

	private CryptoDeposit updateCryptoDeposit(Client client, Operation operation) {

		CryptoDeposit deposit = (CryptoDeposit) client.getDeposits().get(operation.getCoinType());
		Map<Double, Double> pricePerCryptoQtyMap = new HashMap<>();

		if (deposit.getPricePerCryptoQty() != null) {
			pricePerCryptoQtyMap = deposit.getPricePerCryptoQty();
		}

		Double coinPrice = operation.getCoinPrice();
		Double coinQty = operation.getCoinQty();
		Double qtyForSave = 0.0;

		qtyForSave = calculateQtyForSave(operation, pricePerCryptoQtyMap, coinPrice, coinQty);

		if (qtyForSave > 0.0) {
			pricePerCryptoQtyMap.put(coinPrice, qtyForSave);
		} else {
			pricePerCryptoQtyMap.remove(coinPrice);
		}

		deposit.setPricePerCryptoQty(pricePerCryptoQtyMap);

		return deposit;
	}

	private Double calculateQtyForSave(Operation operation, Map<Double, Double> pricePerCryptoQtyMap, Double coinPrice, Double coinQty) {
		Double qtyForSave = 0.0;

		if (OperationType.BUY.equals(operation.getOperationType())) {
			qtyForSave = updateCryptoDepositForBuy(pricePerCryptoQtyMap, coinPrice, coinQty);
		} else if (OperationType.SELL.equals(operation.getOperationType())) {
			qtyForSave = updateCryptoDepositForSell(pricePerCryptoQtyMap, coinPrice, coinQty);
		}

		return qtyForSave;
	}

	private Double updateCryptoDepositForBuy(Map<Double, Double> pricePerCryptoQtyMap, Double coinPrice, Double coinQty) {
		Double qtyForSave = 0.0;
		Double oldQty = searchForOldQty(pricePerCryptoQtyMap, coinPrice);

		qtyForSave = oldQty + coinQty;

		return qtyForSave;
	}

	private Double updateCryptoDepositForSell(Map<Double, Double> pricePerCryptoQtyMap, Double coinPrice,
			Double coinQty) {

		Double qtyForSave = 0.0;
		Double oldQty = searchForOldQty(pricePerCryptoQtyMap, coinPrice);
		if (oldQty <= 0) {
			qtyForSave = coinQty;
		} else {
			qtyForSave = oldQty - coinQty;
		}

		return qtyForSave;
	}

	private Double searchForOldQty(Map<Double, Double> pricePerCryptoQtyMap, Double coinPrice) {
		Double oldQty = 0.0;

		if (pricePerCryptoQtyMap.containsKey(coinPrice)) {
			oldQty = pricePerCryptoQtyMap.get(coinPrice);
		}

		return oldQty;
	}
}
