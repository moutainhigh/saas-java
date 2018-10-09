package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum CompareEnum {
	lt("小于"), 
	gt("大于"),
	eq("等于"),
	
	;

	private String descript;

	private CompareEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static CompareEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
