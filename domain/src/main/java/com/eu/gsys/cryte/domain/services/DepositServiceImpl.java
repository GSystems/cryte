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

		return deposit;
	}

	private CurrencyDeposit updateCurrencyDeposit(Client client, Operation operation) {
		CurrencyDeposit deposit = (CurrencyDeposit) client.getDeposits().get(operation.getCoinType());


		return deposit;
	}

	private CryptoDeposit updateCryptoDeposit(Client client, Operation operation) {

		Double coinPrice = operation.getCoinPrice();
		Double newCoinQty = operation.getCoinQty();
		Double qtyForSave = 0.0;

		CryptoDeposit deposit = (CryptoDeposit) client.getDeposits().get(operation.getCoinType());

		Double oldProfit = deposit.getProfitCtv();
		Double oldPricePerCoin = deposit.getPricePerCoin();

		Pair<Double, Double> newCryptoQtyAtPrice;
		Pair<Double, Double> oldCryptoQtyAtPrice = Pair.of(0.0, 0.0);

		if (deposit.getPricePerCryptoQty() != null) {
			oldCryptoQtyAtPrice = deposit.getPricePerCryptoQty();
		}

		newCryptoQtyAtPrice = calculateQtyAndPriceForSave(oldCryptoQtyAtPrice, operation);

		if (newCryptoQtyAtPrice.getSecond() < 0) {
			newCryptoQtyAtPrice = Pair.of(newCryptoQtyAtPrice.getFirst(), 0.0);
		}

		deposit.setPricePerCryptoQty(newCryptoQtyAtPrice);

		Pair<Double, Double> profitAndNewPricePerCoin = calculateProfitAndNewPricePerCoin(deposit);

		deposit.setProfitCtv(profitAndNewPricePerCoin.getFirst());
		deposit.setPricePerCoin(profitAndNewPricePerCoin.getSecond());

		return deposit;
	}

	private Pair<Double, Double> calculateQtyAndPriceForSave(Pair<Double, Double> cryptoQtyAtPrice, Operation operation) {
		Pair<Double, Double> newCryptoQtyAtPrice;

		Double qtyForSave = 0.0;
		Double oldQty = cryptoQtyAtPrice.getFirst();
		Double newQty = operation.getCoinQty();

		Double priceForSave = 0.0;
		Double oldPrice = cryptoQtyAtPrice.getSecond();
		Double newPrice = operation.getCoinPrice();

		if (OperationType.BUY.equals(operation.getOperationType())) {
			qtyForSave = oldQty + newQty;
			priceForSave = oldPrice + newPrice;
		} else if (OperationType.SELL.equals(operation.getOperationType())) {
			qtyForSave = oldQty - newQty;
			priceForSave = oldPrice - newPrice;
		}

		newCryptoQtyAtPrice = Pair.of(qtyForSave, priceForSave);

		return newCryptoQtyAtPrice;
	}

	private Pair<Double, Double> calculateProfitAndNewPricePerCoin(CryptoDeposit deposit) {
		Pair<Double, Double> profitAndPricePerCoin;

		Double newProfit = deposit.getProfitCtv();
		Double newPricePerCoin = 0.0;
		Double actualQty = deposit.getPricePerCryptoQty().getFirst();
		Double actualPrice = deposit.getPricePerCryptoQty().getSecond();

		if (actualPrice < 0) {
			newProfit += actualPrice;
		} else {
			newProfit -= actualPrice;
		}

		newPricePerCoin = actualPrice / actualQty;

		profitAndPricePerCoin = Pair.of(newProfit, newPricePerCoin);

		return profitAndPricePerCoin;
	}
}
