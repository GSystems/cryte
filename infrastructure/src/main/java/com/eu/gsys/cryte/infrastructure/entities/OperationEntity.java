package com.eu.gsys.cryte.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class OperationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String coinName;
	private String coinId;
	private Double coinPrice;
	private Double coinQty;
	private Double operationCtv;
	private Double feeCtv;
	private Double feePerc;
	private Double minimumWithdrawalCoinPrice;
	private LocalDate date;
	private String currency;
	private Double currencyPrice;
	private String operationType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}
