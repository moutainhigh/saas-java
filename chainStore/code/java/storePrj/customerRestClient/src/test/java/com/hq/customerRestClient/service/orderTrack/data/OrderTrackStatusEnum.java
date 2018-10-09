package com.hq.customerRestClient.service.orderTrack.data;

public enum OrderTrackStatusEnum {
	New("待付款"), 
	Pay("待发货"),
	Send("已发货"),
	Finish("已完成"),
	;

	private String mark;

	private OrderTrackStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OrderTrackStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
