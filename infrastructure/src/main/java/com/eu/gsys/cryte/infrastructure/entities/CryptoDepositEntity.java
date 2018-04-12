package com.eu.gsys.cryte.infrastructure.entities;

import javax.persistence.Entity;

@Entity
public class CryptoDepositEntity extends GenericDepositEntity {

    private Double coinQty;
    private Double totalOperationCtv;
    private Double profitCtv;
    private Double pricePerCoin;

    public Double getCoinQty() {
        return coinQty;
    }

    public void setCoinQty(Double coinQty) {
        this.coinQty = coinQty;
    }

    public Double getTotalOperationCtv() {
        return totalOperationCtv;
    }

    public void setTotalOperationCtv(Double totalOperationCtv) {
        this.totalOperationCtv = totalOperationCtv;
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
}
