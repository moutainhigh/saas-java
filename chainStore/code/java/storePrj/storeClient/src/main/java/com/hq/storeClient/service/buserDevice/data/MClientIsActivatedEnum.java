package com.hq.storeClient.service.buserDevice.data;

//@Deprecated
public enum MClientIsActivatedEnum {
	False("已出厂"),
	True("已激活"),
	;

	private String mark;

	private MClientIsActivatedEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MClientIsActivatedEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
