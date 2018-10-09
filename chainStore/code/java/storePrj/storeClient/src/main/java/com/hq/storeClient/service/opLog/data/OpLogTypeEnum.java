package com.hq.storeClient.service.opLog.data;


public enum OpLogTypeEnum{
	Leaguer("会员"),
	Product("产品"),
	Clerk("员工"),
	Appoint("预约"),
	Order("订单"),
	;
	
	private String mark;
	
	private OpLogTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OpLogTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
