package com.hq.storeClient.service.common;

public enum ExpiredStateEnum {
	UNEXPIRED("未过期"), 
	EXPIRED("已过期"), 
	;

	private String mark;

	private ExpiredStateEnum(String mark) {
		this.mark = mark;
	}

	public static ExpiredStateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
