package com.hq.chainStore.service.activateCode.data;


public enum ActivateCodeStatusEnum{
	UNKNOW("未知"),
	UNVALID("无效"),
	VALID("有效"),
	;
	
	private String mark;
	
	private ActivateCodeStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ActivateCodeStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
