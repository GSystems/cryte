package com.eu.gsys.cryte.web.util;

public enum CoinEnum {

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
