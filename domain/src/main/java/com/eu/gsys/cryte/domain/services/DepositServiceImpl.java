package com.eu.gsys.cryte.domain.services;

import com.eu.gsys.cryte.domain.models.*;
import com.eu.gsys.cryte.domain.transformers.DepositTransformer;
import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;
import com.eu.gsys.infrastructure.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public GenericDeposit updateDeposit(Client client, BuySellOperation buySellOperation) {

		GenericDeposit deposit = client.getDeposits().get(buySellOperation.getCoinId());

		if (CoinType.EURO.getCode().equals(buySellOperation.getCoinId())) {
			deposit = updateCurrencyDeposit(client, buySellOperation);
		} else {
			deposit = updateCryptoDeposit(client, buySellOperation);
		}

		return deposit;
	}

	private CurrencyDeposit updateCurrencyDeposit(Client client, BuySellOperation buySellOperation) {
		CurrencyDeposit deposit = (CurrencyDeposit) client.getDeposits().get(buySellOperation.getCoinId());


		return deposit;
	}

	private CryptoDeposit updateCryptoDeposit(Client client, BuySellOperation buySellOperation) {
		CryptoDeposit deposit = (CryptoDeposit) client.getDeposits().get(buySellOperation.getCoinId());
		Map<Double, Double> pricePerCryptoQtyMap = deposit.getPricePerCryptoQty();

		Double coinPrice = buySellOperation.getCoinPrice();
		Double coinQty = buySellOperation.getCoinQty();
		Double qtyForSave = 0.0;

		if (OperationType.BUY.getCode().equals(buySellOperation.getOperationType())) {
			qtyForSave = updateCryptoDepositForBuy(pricePerCryptoQtyMap, coinPrice, coinQty);
			pricePerCryptoQtyMap.put(coinPrice, qtyForSave);
		} else if (OperationType.SELL.getCode().equals(buySellOperation.getOperationType())){
			updateCryptoDepositForSell(pricePerCryptoQtyMap, coinPrice, coinQty);

		}

		return deposit;
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
