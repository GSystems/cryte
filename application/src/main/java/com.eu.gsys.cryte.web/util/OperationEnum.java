package com.eu.gsys.cryte.web.util;

public enum OperationEnum {

	BUY("B"),
	SELL("S");

	private String code;

	OperationEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
