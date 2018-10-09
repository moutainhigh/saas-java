package com.hq.payms.service.bossPayInfo.data;

public enum WxpayModelEnum {
	NONE("未选择"),
	NORMAL("普通商户模式"),
	PROVIDER("服务商模式"),
	;
	
	private String mark;
	
	private WxpayModelEnum(String mark){
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static WxpayModelEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
