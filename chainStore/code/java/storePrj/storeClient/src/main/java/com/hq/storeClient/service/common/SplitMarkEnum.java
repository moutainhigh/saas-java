package com.hq.storeClient.service.common;


public enum SplitMarkEnum {
	AWAIT("等待"),
	FINISH("完成"), 
	FINISH_LEVEL2("升级2"), 
	FINISH_LEVEL3("升级3"), 
	FINISH_LEVEL4("升级4"), 
	FINISH_LEVEL5("升级5"), 
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
