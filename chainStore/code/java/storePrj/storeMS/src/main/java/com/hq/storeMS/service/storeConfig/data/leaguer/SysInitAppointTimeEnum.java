package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum SysInitAppointTimeEnum {
	STANDARD_TIME("09:00", "23:00", "标准营业时间"),
	;

	private String startTime;
	private String endTime;
	private String mark;

	private SysInitAppointTimeEnum(String startTimeP, String endTimeP, String markP) {
		this.startTime = startTimeP;
		this.endTime = endTimeP;
		this.mark = markP;
	}

	public static SysInitAppointTimeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getMark() {
		return mark;
	}
}
