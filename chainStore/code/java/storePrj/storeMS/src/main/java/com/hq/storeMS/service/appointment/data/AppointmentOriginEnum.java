package com.hq.storeMS.service.appointment.data;

@Deprecated
public enum AppointmentOriginEnum{
	UNKNOW("未知"),
	FROM_STORE_CLERK("商家"),
	FROM_STORE_BEAUTICIAN("医美师"),
	FROM_CUSTOMER("C端用户"),
	;
	
	private String mark;
	
	private AppointmentOriginEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static AppointmentOriginEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
