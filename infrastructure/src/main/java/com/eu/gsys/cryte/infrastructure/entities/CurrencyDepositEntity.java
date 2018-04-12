package com.eu.gsys.cryte.infrastructure.entities;

import javax.persistence.Entity;

@Entity
public class CurrencyDepositEntity extends GenericDepositEntity {

    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
