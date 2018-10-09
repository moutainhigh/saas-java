package com.hq.storeMS.service.appointment.data;


public enum AppointFromEnums{
	UNKNOW("未知"),
	STORE("自建预约"),
	CUSTOMER("客户预约"),
	;
	
	private String mark;
	
	private AppointFromEnums(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static AppointFromEnums valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
