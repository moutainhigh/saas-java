package com.hq.chainClient.service.sellProduct.apiData;

public enum SellProductUpdateType {
	AllotSellProduct("分配"),
	BatchAllotSellProduct("批量分配"),
	
	UpdateSellProductState("上下架"),
	BatchUpdateSellProductState("批量上下架"),
	
	;

	private String descript;

	private SellProductUpdateType(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static SellProductUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
