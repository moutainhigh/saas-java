package com.hq.chainStore.service.chainCard.data;

public enum SellFlagEnum {
	NOT_SELL("未销售"),
	HAS_SELL("已销售"), 
	;

	private String mark;

	private SellFlagEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static SellFlagEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
