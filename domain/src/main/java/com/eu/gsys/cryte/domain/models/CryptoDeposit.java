package com.eu.gsys.cryte.domain.models;

import org.springframework.data.util.Pair;

import java.util.Objects;

public class CryptoDeposit extends GenericDeposit {

	private Pair<Double, Double> pricePerCryptoQty;
	private Double profitCtv;
	private Double pricePerCoin;

	public Pair<Double, Double> getPricePerCryptoQty() {
		return pricePerCryptoQty;
	}

	public void setPricePerCryptoQty(Pair<Double, Double> pricePerCryptoQty) {
		this.pricePerCryptoQty = pricePerCryptoQty;
	}

	public Double getProfitCtv() {
		return profitCtv;
	}

	public void setProfitCtv(Double profitCtv) {
		this.profitCtv = profitCtv;
	}

	public Double getPricePerCoin() {
		return pricePerCoin;
	}

	public void setPricePerCoin(Double pricePerCoin) {
		this.pricePerCoin = pricePerCoin;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		CryptoDeposit that = (CryptoDeposit) o;
		return Objects.equals(pricePerCryptoQty, that.pricePerCryptoQty) && Objects.equals(profitCtv, that.profitCtv)
				&& Objects.equals(pricePerCoin, that.pricePerCoin);
	}

	@Override public int hashCode() {

		return Objects.hash(super.hashCode(), pricePerCryptoQty, profitCtv, pricePerCoin);
	}
}
