package com.hq.storeMS.service.storeChain.data;

public enum StoreChainStatus {
	Open("上架"),
	Close("下架"),
	;

	private String descript;

	private StoreChainStatus(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static StoreChainStatus valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
