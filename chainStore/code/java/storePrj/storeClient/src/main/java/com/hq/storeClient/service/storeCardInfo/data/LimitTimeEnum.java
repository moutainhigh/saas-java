package com.hq.storeClient.service.storeCardInfo.data;


public enum LimitTimeEnum {
	NEVER(0, "永久"),
	TEN_YEAR(120, "10年"), 
	FIVE_YEAR(60, "5年"), 
	THREE_YEAR(36, "3年"), 
	ONE_YEAR(12, "1年"), 
	HALF_YEAR(6, "半年"), 
	THREE_MONTH(3, "3个月"), 
	ONE_MONTH(1, "1个月"), 
	;

	private int month;
	private String mark;

	private LimitTimeEnum(int month, String mark) {
		this.month = month;
		this.mark = mark;
	}

	public int getMonth() {
		return month;
	}

	public String getMark() {
		return mark;
	}

	public static LimitTimeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
