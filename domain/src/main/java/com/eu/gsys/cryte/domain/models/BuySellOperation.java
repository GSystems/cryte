package com.eu.gsys.cryte.domain.models;

import com.eu.gsys.cryte.domain.util.OperationEnum;

import java.time.LocalDate;
import java.util.Objects;

public class BuySellOperation {

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
	private String currency;
	private Double currencyPrice;
	private OperationEnum operationType;

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

	public OperationEnum getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationEnum operationType) {
		this.operationType = operationType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BuySellOperation that = (BuySellOperation) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(coinName, that.coinName) &&
				Objects.equals(coinId, that.coinId) &&
				Objects.equals(coinPrice, that.coinPrice) &&
				Objects.equals(coinQty, that.coinQty) &&
				Objects.equals(operationCtv, that.operationCtv) &&
				Objects.equals(feePerc, that.feePerc) &&
				Objects.equals(feeCtv, that.feeCtv) &&
				Objects.equals(minimumWithdrawalCoinPrice, that.minimumWithdrawalCoinPrice) &&
				Objects.equals(date, that.date) &&
				Objects.equals(currency, that.currency) &&
				Objects.equals(currencyPrice, that.currencyPrice) &&
				operationType == that.operationType;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, coinName, coinId, coinPrice, coinQty, operationCtv, feePerc, feeCtv, minimumWithdrawalCoinPrice, date, currency, currencyPrice, operationType);
	}
}
