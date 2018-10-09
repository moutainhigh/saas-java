package com.hq.orderRestClient.service.order.data;

public enum OrderOriginEnum {
	BUSINESS("B端"),
	CUSTOMER("C端"),
	;
	
	private String mark;
	
	private OrderOriginEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderOriginEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
