package com.hq.customerRestClient.common.dataSyn.info;

public enum DataSynType {
	Store,
	;
	
	public static DataSynType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
