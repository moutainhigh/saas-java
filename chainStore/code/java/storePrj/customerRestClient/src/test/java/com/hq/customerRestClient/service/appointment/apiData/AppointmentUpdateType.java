package com.hq.customerRestClient.service.appointment.apiData;

public enum AppointmentUpdateType {
	UpdateInfo("修改预约基本信息"),
	UpdateState("修改预约状态"),
	DeleteAppoint("删除预约"),
	
	;
	
	private String mark;
	
	private AppointmentUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static AppointmentUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
