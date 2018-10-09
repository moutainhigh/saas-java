package com.hq.storeMS.service.buserDevice.data;

public enum MClientBandingSystemEnum {
	None("未绑定系统"),
	ZMT("智美通"),
	;

	private String mark;

	private MClientBandingSystemEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MClientBandingSystemEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
