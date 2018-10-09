package com.hq.storeClient.service.storeCardInfo.data;

public enum CardStatusEnum {
	NEW("未上架"), //未启用
	OPEN("已上架"), //启用
	CLOSE("已下架"), 
	;

	private String mark;

	private CardStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static CardStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
