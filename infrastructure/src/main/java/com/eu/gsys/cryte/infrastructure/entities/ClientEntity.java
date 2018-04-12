package com.eu.gsys.cryte.infrastructure.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Double payedFeesCtv;
	private Double profitCtv;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<CryptoDepositEntity> cryptoDeposits;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<CurrencyDepositEntity> currencyDeposits;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<OperationEntity> operations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getPayedFeesCtv() {
		return payedFeesCtv;
	}

	public void setPayedFeesCtv(Double payedFeesCtv) {
		this.payedFeesCtv = payedFeesCtv;
	}

	public Double getProfitCtv() {
		return profitCtv;
	}

	public void setProfitCtv(Double profitCtv) {
		this.profitCtv = profitCtv;
	}

	public List<CryptoDepositEntity> getCryptoDeposits() {
		return cryptoDeposits;
	}

	public void setCryptoDeposits(List<CryptoDepositEntity> cryptoDeposits) {
		this.cryptoDeposits = cryptoDeposits;
	}

	public List<CurrencyDepositEntity> getCurrencyDeposits() {
		return currencyDeposits;
	}

	public void setCurrencyDeposits(List<CurrencyDepositEntity> currencyDeposits) {
		this.currencyDeposits = currencyDeposits;
	}

	public List<OperationEntity> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationEntity> operations) {
		this.operations = operations;
	}
}
