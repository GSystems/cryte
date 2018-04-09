package com.eu.gsys.infrastructure.entities;

import javax.persistence.Entity;

@Entity
public class CurrencyGenericDeposit extends GenericDeposit {

    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
