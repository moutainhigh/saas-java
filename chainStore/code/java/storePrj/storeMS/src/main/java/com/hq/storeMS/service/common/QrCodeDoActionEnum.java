package com.hq.storeMS.service.common;

//二维码 操作枚举
public enum QrCodeDoActionEnum {
	UNKNOW("未知"),
	JoinStore("加入店铺"),
	;
	
	private String mark;
	
	private QrCodeDoActionEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static QrCodeDoActionEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
