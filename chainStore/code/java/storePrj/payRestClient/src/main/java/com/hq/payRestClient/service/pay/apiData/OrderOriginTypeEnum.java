package com.hq.payRestClient.service.pay.apiData;

public enum OrderOriginTypeEnum {
	STOREMS_ORDER("storeMS订单"), 
	STOREMNGMS_CHARGE("storeManagerMS收费记录"), 
	;

	private String mark;

	private OrderOriginTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderOriginTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
