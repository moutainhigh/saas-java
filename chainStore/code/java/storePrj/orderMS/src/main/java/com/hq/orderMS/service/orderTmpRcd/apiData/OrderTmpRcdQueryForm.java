package com.hq.orderMS.service.orderTmpRcd.apiData;

public class OrderTmpRcdQueryForm {
	private long minTime;
	// 订单来源 OrderOriginEnum
	private int origin;

	public static OrderTmpRcdQueryForm newInstance() {
		return new OrderTmpRcdQueryForm();
	}

	public long getMinTime() {
		return minTime;
	}

	public OrderTmpRcdQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public int getOrigin() {
		return origin;
	}

	public OrderTmpRcdQueryForm setOrigin(int origin) {
		this.origin = origin;
		return this;
	}
}
