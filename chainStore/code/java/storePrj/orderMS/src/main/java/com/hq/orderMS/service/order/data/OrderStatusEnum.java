package com.hq.orderMS.service.order.data;

public enum OrderStatusEnum {
	NOT_PAY("未支付"),
	HAS_PAY("已支付"),
	CANCEL("已取消"),
	CHARGEBACK_ALL("已退单"),
	CHARGEBACK_PART("部分退单"),
	;

	private String mark;

	private OrderStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
