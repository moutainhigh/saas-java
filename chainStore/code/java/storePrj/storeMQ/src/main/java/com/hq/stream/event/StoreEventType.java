package com.hq.stream.event;

public enum StoreEventType {
	UnKnown,  //未知事件
	LeaguerBirthdayNotice, //生日提醒
	OrderCancelHook, //订单过时取消事件
	;

	public static StoreEventType valueOf(int ordinal) {
		
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
