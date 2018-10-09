package com.hq.chainStore.service.chainCard.data;

public enum ValidPeriodUnitEnum {
	FOREVER("永久"),
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
