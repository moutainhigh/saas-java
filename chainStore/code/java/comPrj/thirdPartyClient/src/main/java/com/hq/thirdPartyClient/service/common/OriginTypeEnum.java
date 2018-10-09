package com.hq.thirdPartyClient.service.common;

public enum OriginTypeEnum {
	UNKNOW("未知"),
	STORE("B端"),
	CUSTOMER("C端"),
	;
	
	private String mark;
	
	private OriginTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OriginTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
