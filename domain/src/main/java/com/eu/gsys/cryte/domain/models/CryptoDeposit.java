package com.eu.gsys.cryte.domain.models;

import java.util.Map;

public class CryptoDeposit extends GenericDeposit {

	private Map<Double, Double> pricePerCryptoQty;

	public Map<Double, Double> getPricePerCryptoQty() {
		return pricePerCryptoQty;
	}

	public void setPricePerCryptoQty(Map<Double, Double> pricePerCryptoQty) {
		this.pricePerCryptoQty = pricePerCryptoQty;
	}
}
