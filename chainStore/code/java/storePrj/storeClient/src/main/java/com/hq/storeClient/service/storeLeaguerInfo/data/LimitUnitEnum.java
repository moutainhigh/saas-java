package com.hq.storeClient.service.storeLeaguerInfo.data;

public enum LimitUnitEnum {
	EMPTY(""),
	DAY("天"),
	MONTH("月"), 
	YEAR("年"), 
	;

	private String mark;

	private LimitUnitEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static LimitUnitEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
