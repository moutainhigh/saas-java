package com.hq.chainMS.service.common;


public enum SplitMarkEnum {
	LEVEL1("等级1"), 
	LEVEL2("等级2"), 
	LEVEL3("等级3"), 
	LEVEL4("等级4"), 
	LEVEL5("等级5"), 
	;

	private String mark;

	private SplitMarkEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static SplitMarkEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
