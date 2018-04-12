package com.eu.gsys.cryte.domain.util;

public enum OperationType {

	BUY("B"),
	SELL("S"),
	FUND("F");

	private String code;

	OperationType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
