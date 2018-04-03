package com.eu.gsys.cryte.domain.util;

public enum CoinType {

	EURO("EUR"),
	BITCOIN("XBT"),
	ETHEREUM("ETH");

	private String code;

	CoinType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
