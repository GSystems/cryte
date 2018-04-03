package com.eu.gsys.cryte.domain.util;

public enum FeePercEnum {

	XBT(0.2551),
	ETH(0.26);

	private Double feePerc;

	FeePercEnum(Double feePerc) {
		this.feePerc = feePerc;
	}

	public Double getFeePerc() {
		return feePerc;
	}
}
