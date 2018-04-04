package com.eu.gsys.cryte.domain.models;

import com.eu.gsys.cryte.domain.util.CoinType;

import java.util.Objects;

public class Deposit {

	private Integer id;
	private String coinId;
	private String coinName;
	private Double ctv;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoinId() {
		return coinId;
	}

	public void setCoinId(String coinId) {
		this.coinId = coinId;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public Double getCtv() {
		return ctv;
	}

	public void setCtv(Double ctv) {
		this.ctv = ctv;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Deposit deposit = (Deposit) o;
		return Objects.equals(id, deposit.id) &&
				coinId == deposit.coinId &&
				Objects.equals(coinName, deposit.coinName) &&
				Objects.equals(ctv, deposit.ctv);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, coinId, coinName, ctv);
	}
}
