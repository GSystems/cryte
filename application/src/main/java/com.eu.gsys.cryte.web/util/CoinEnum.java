package com.eu.gsys.cryte.web.util;

public enum CoinEnum {

	EURO("EUR"),
	BITCOIN("XBT"),
	ETHEREUM("ETH");

	private String code;

	CoinEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}