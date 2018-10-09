package com.hq.customerRestClient.service.appointment.data;

public enum AppointmentStatusEnum {
	NEW("未接受"), 
	RECEIVE("已接受"), 
	CANCEL("已取消"),
	SUCCESS("已完成"),
	;
	
	private String mark;
	
	private AppointmentStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static AppointmentStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
