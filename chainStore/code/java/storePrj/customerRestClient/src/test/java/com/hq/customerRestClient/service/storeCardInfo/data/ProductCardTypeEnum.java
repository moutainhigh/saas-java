package com.hq.customerRestClient.service.storeCardInfo.data;

public enum ProductCardTypeEnum {

	LIMIT_PRDANDTIME("指定项目及次数"),
	LIMIT_TIMEBUTPRD("限次数不限项目"), 
	NOLIMIT_PRDANDTIME("不限次数不限项目"), 
	;

	private String mark;

	private ProductCardTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ProductCardTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
