package com.hq.chainStore.service.storeLeaguerInfo.apiData;


public enum ServiceTypeEnum {
	ORDER("订单服务"), 
	APPOINTMENT("预约服务"),

	;

	private String mark;

	private ServiceTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ServiceTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
