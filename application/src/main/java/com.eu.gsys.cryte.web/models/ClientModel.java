package com.eu.gsys.cryte.web.models;

import java.util.List;
import java.util.Map;

public class ClientModel {

	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private Double payedFeesCtv;
	private Double profitCtv;
	private Map<String, DepositModel> deposits;
	private List<OperationModel> operations;

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

	public Map<String, DepositModel> getDeposits() {
		return deposits;
	}

	public void setDeposits(Map<String, DepositModel> deposits) {
		this.deposits = deposits;
	}

	public List<OperationModel> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationModel> operations) {
		this.operations = operations;
	}
}