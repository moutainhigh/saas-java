package com.hq.storeMS.service.common;

public enum UseFlagEnum {
	Disable("禁用"),
	Enable("启用"), 
	;
	
	private String mark;
	
	private UseFlagEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static UseFlagEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
