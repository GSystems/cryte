package com.eu.gsys.cryte.domain.models;

import java.util.Map;
import java.util.Objects;

public class CryptoDeposit extends GenericDeposit {

	private Map<Double, Double> pricePerCryptoQty;

	public Map<Double, Double> getPricePerCryptoQty() {
		return pricePerCryptoQty;
	}

	public void setPricePerCryptoQty(Map<Double, Double> pricePerCryptoQty) {
		this.pricePerCryptoQty = pricePerCryptoQty;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		CryptoDeposit that = (CryptoDeposit) o;
		return Objects.equals(pricePerCryptoQty, that.pricePerCryptoQty);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), pricePerCryptoQty);
	}
}
