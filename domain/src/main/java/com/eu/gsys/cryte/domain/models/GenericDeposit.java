package com.eu.gsys.cryte.domain.models;

import com.eu.gsys.cryte.domain.util.CoinType;

import java.util.Objects;

public class GenericDeposit {

	private Integer id;
	private CoinType coinType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CoinType getCoinType() {
		return coinType;
	}

	public void setCoinType(CoinType coinType) {
		this.coinType = coinType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GenericDeposit deposit = (GenericDeposit) o;
		return Objects.equals(id, deposit.id) && coinType == deposit.coinType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, coinType);
	}
}
