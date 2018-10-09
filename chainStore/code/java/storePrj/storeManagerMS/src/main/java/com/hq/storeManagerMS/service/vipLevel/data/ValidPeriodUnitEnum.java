package com.hq.storeManagerMS.service.vipLevel.data;

public enum ValidPeriodUnitEnum {
	DAY("天"),
	MONTH("月"), 
	YEAR("年"), 
	;

	private String mark;

	private ValidPeriodUnitEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ValidPeriodUnitEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
