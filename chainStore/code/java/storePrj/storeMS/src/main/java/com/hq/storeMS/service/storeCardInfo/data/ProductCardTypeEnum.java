package com.hq.storeMS.service.storeCardInfo.data;

public enum ProductCardTypeEnum {

	LIMIT_PRDANDTIME("指定项目及次数"),
	LIMIT_TIMEBUTPRD("不限项目限次数"), 
	NOLIMIT_PRDANDTIME("不限项目不限次数"), 
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
