package com.hq.storeClient.service.appointment.data;

public enum OperateTypeEnum {
	CASH("现结"), 
	SWINGCARD("划卡"), 
	;
	
	private String mark;
	
	private OperateTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static OperateTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
