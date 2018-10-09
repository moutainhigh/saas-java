package com.hq.storeMS.service.storeConfig.data.chain;

public enum ShareEnum {
	NATIVE("不共享"), 
	SHARE("共享"),
	;

	private String descript;

	private ShareEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static ShareEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
