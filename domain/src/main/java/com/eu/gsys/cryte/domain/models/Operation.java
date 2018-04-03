package com.eu.gsys.cryte.domain.models;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {

	private Long id;
	private String coinName;
	private String coinId;
	private Double coinPrice;
	private Double coinQty;
	private Double operationCtv;
	private Double feePerc;
	private Double feeCtv;
	private Double minimumWithdrawalCoinPrice;
	private LocalDate date;
	private Boolean isBuy;
	private Boolean isSell;
	private String currency;
	private Double currencyPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getCoinId() {
		return coinId;
	}

	public void setCoinId(String coinId) {
		this.coinId = coinId;
	}

	public Double getCoinPrice() {
		return coinPrice;
	}

	public void setCoinPrice(Double coinPrice) {
		this.coinPrice = coinPrice;
	}

	public Double getCoinQty() {
		return coinQty;
	}

	public void setCoinQty(Double coinQty) {
		this.coinQty = coinQty;
	}

	public Double getOperationCtv() {
		return operationCtv;
	}

	public void setOperationCtv(Double operationCtv) {
		this.operationCtv = operationCtv;
	}

	public Double getFeeCtv() {
		return feeCtv;
	}

	public void setFeeCtv(Double feeCtv) {
		this.feeCtv = feeCtv;
	}

	public Double getFeePerc() {
		return feePerc;
	}

	public void setFeePerc(Double feePerc) {
		this.feePerc = feePerc;
	}

	public Double getMinimumWithdrawalCoinPrice() {
		return minimumWithdrawalCoinPrice;
	}

	public void setMinimumWithdrawalCoinPrice(Double minimumWithdrawalCoinPrice) {
		this.minimumWithdrawalCoinPrice = minimumWithdrawalCoinPrice;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getBuy() {
		return isBuy;
	}

	public void setBuy(Boolean buy) {
		isBuy = buy;
	}

	public Boolean getSell() {
		return isSell;
	}

	public void setSell(Boolean sell) {
		isSell = sell;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getCurrencyPrice() {
		return currencyPrice;
	}

	public void setCurrencyPrice(Double currencyPrice) {
		this.currencyPrice = currencyPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Operation operation = (Operation) o;
		return Objects.equals(id, operation.id) && Objects.equals(coinName, operation.coinName) && Objects
				.equals(coinId, operation.coinId) && Objects.equals(coinPrice, operation.coinPrice) && Objects
				.equals(coinQty, operation.coinQty) && Objects.equals(operationCtv, operation.operationCtv) && Objects
				.equals(feePerc, operation.feePerc) && Objects.equals(feeCtv, operation.feeCtv) && Objects
				.equals(minimumWithdrawalCoinPrice, operation.minimumWithdrawalCoinPrice) && Objects.equals(date, operation.date)
				&& Objects.equals(isBuy, operation.isBuy) && Objects.equals(isSell, operation.isSell) && Objects
				.equals(currency, operation.currency) && Objects.equals(currencyPrice, operation.currencyPrice);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(id, coinName, coinId, coinPrice, coinQty, operationCtv, feePerc, feeCtv,
						minimumWithdrawalCoinPrice, date,
						isBuy, isSell, currency, currencyPrice);
	}
}
