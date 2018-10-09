package com.hq.storeMS.service.common;

public enum DataOriginEnum {
	ZMT("智美通"),
	CHAIN("连锁店"), 
	;

	private String mark;

	private DataOriginEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static DataOriginEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
