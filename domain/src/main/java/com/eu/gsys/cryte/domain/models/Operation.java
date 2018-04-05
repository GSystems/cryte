package com.eu.gsys.cryte.domain.models;

import com.eu.gsys.cryte.domain.util.CoinType;
import com.eu.gsys.cryte.domain.util.OperationType;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {

	private Integer id;
	private CoinType coinType;
	private Double coinPrice;
	private Double coinQty;
	private Double operationCtv;
	private Double feePerc;
	private Double feeCtv;
	private Double minimumWithdrawalCoinPrice;
	private LocalDate date;
	private String currency;
	private Double currencyPrice;
	private OperationType operationType;

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

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Operation operation = (Operation) o;
		return Objects.equals(id, operation.id) &&
				coinType == operation.coinType &&
				Objects.equals(coinPrice, operation.coinPrice) &&
				Objects.equals(coinQty, operation.coinQty) &&
				Objects.equals(operationCtv, operation.operationCtv) &&
				Objects.equals(feePerc, operation.feePerc) &&
				Objects.equals(feeCtv, operation.feeCtv) &&
				Objects.equals(minimumWithdrawalCoinPrice, operation.minimumWithdrawalCoinPrice) &&
				Objects.equals(date, operation.date) &&
				Objects.equals(currency, operation.currency) &&
				Objects.equals(currencyPrice, operation.currencyPrice) &&
				operationType == operation.operationType;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, coinType, coinPrice, coinQty, operationCtv, feePerc, feeCtv, minimumWithdrawalCoinPrice, date, currency, currencyPrice, operationType);
	}
}
