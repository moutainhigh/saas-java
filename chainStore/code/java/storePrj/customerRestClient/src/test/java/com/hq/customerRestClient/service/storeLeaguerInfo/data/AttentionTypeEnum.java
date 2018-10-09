package com.hq.customerRestClient.service.storeLeaguerInfo.data;


public enum AttentionTypeEnum {
	UNKNOW("未知"), 
	STAR("星客"),

	;

	private String mark;

	private AttentionTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static AttentionTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
