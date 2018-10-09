package com.hq.storeMS.service.common;

public enum LoadDataEnum {
	CACHE("缓存"), 
	DB("数据库"), 
	;

	private String mark;

	private LoadDataEnum(String mark) {
		this.mark = mark;
	}

	public static LoadDataEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
