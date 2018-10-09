package com.hq.storeMS.service.storeChain.apiData;

public enum StoreChainUpdateType {
	BatchUpdateState("批量修改连锁店产品的状态"),
	
	;

	private String descript;

	private StoreChainUpdateType(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static StoreChainUpdateType valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
