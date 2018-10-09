package com.hq.storeManagerMS.service.mngDevice.data.mclient;

public enum MClientStatusEnum {
	Offline("离线"),
	Online("在线"),
	;

	private String mark;

	private MClientStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MClientStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
