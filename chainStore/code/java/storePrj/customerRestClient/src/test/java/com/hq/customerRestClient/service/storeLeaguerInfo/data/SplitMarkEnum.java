package com.hq.customerRestClient.service.storeLeaguerInfo.data;


public enum SplitMarkEnum {
	AWAIT("等待"),
	FINISH("完成"), 
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
