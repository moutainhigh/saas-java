package com.hq.chainStore.service.storeCardInfo.data;

public enum CardTypeEnum {
	UNKNOW("未知"), 
	DISCOUNT("打折卡"), 
	CASH_BACK("返现卡"), 
	;

	private String mark;

	private CardTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static CardTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
