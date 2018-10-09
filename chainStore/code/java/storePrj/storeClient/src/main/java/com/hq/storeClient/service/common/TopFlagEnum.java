package com.hq.storeClient.service.common;

public enum TopFlagEnum {
	Normal("普通"),
	Top("置顶"),
	;
	private String mark;
	
	private TopFlagEnum(String markP) {
		this.mark = markP;
	}

	public static TopFlagEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

	public String getMark() {
		return mark;
	}
}
