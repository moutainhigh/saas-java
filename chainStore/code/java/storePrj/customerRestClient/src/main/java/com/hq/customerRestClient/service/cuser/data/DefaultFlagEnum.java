package com.hq.customerRestClient.service.cuser.data;

public enum DefaultFlagEnum {
	NON_DEFAULT("非默认"), 
	IS_DEFAULT("默认"),
	;

	private String mark;

	private DefaultFlagEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static DefaultFlagEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}