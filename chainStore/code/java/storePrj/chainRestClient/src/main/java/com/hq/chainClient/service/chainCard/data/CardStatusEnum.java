package com.hq.chainClient.service.chainCard.data;

public enum CardStatusEnum {
	OPEN("已上架"),
	CLOSE("已下架"), 
	;

	private String mark;

	private CardStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static CardStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
