package com.hq.chainStore.service.order.data;

public enum OrderTypeEnum {
	PURCHASE("购买消费"),
	RECHARGE("会员充值"),
	;

	private String mark;

	private OrderTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
