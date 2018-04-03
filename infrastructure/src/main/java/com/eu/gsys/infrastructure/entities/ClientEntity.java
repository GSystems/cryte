package com.eu.gsys.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private List<DepositEntity> deposits;
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

	public List<DepositEntity> getDeposits() {
		return deposits;
	}

	public void setDeposits(List<DepositEntity> deposits) {
		this.deposits = deposits;
	}

	public List<OperationEntity> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationEntity> operations) {
		this.operations = operations;
	}
}
