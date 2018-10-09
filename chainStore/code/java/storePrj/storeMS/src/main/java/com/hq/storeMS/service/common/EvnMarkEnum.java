package com.hq.storeMS.service.common;

//环境枚举
public enum EvnMarkEnum {
	Dev("dev"),
	Prd("prd"),
	Test("test"),
	;
	
	private String mark;
	
	private EvnMarkEnum(String mark) {
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

	public static EvnMarkEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
}
