package com.hq.storeClient.service.storeConfig.data.leaguer;

public enum RequiredEnum {
	Required("必填"), 
	Selection("选填"),
	
	;

	private String descript;

	private RequiredEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static RequiredEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
