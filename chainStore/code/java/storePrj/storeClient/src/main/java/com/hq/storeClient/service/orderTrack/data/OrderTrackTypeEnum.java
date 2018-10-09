package com.hq.storeClient.service.orderTrack.data;

public enum OrderTrackTypeEnum {
	Prestore("到店自提"), 
	Express("快递配送"),
	;

	private String mark;

	private OrderTrackTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderTrackTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
