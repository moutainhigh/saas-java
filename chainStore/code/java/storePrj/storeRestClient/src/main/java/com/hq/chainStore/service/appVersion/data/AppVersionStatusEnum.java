package com.hq.chainStore.service.appVersion.data;


public enum AppVersionStatusEnum{
	UNVALID("无效"),
	VALID("有效"),
	;
	
	private String mark;
	
	private AppVersionStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static AppVersionStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
