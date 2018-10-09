package com.hq.storeClient.service.storeCardInfo.data;

public enum ConsumeTypeEnum {
	UNKNOWN("未知"), 
	COUNT_CARD("次卡"), 
	TIME_CARD("时间卡"), 
	
	;

	private String mark;

	private ConsumeTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ConsumeTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
