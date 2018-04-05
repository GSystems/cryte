package com.eu.gsys.cryte.domain.models;

import org.springframework.data.util.Pair;

import java.util.Objects;

public class CryptoDeposit extends GenericDeposit {

	private Pair<Double, Double> coinQtyAndTotalOpCtv;
	private Double profitCtv;
	private Double pricePerCoin;

	public Pair<Double, Double> getCoinQtyAndTotalOpCtv() {
		return coinQtyAndTotalOpCtv;
	}

	public void setCoinQtyAndTotalOpCtv(Pair<Double, Double> coinQtyAndTotalOpCtv) {
		this.coinQtyAndTotalOpCtv = coinQtyAndTotalOpCtv;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		CryptoDeposit that = (CryptoDeposit) o;
		return Objects.equals(coinQtyAndTotalOpCtv, that.coinQtyAndTotalOpCtv) &&
				Objects.equals(profitCtv, that.profitCtv) &&
				Objects.equals(pricePerCoin, that.pricePerCoin);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), coinQtyAndTotalOpCtv, profitCtv, pricePerCoin);
	}
}
