package com.eu.gsys.cryte.infrastructure.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class GenericDepositEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String coinId;
	private String coinName;
	private Double ctv;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoinId() {
		return coinId;
	}

	public void setCoinId(String coinId) {
		this.coinId = coinId;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public Double getCtv() {
		return ctv;
	}

	public void setCtv(Double ctv) {
		this.ctv = ctv;
	}
}
