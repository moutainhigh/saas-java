package com.hq.storeClient.service.leaguerDetail.data;

public enum LeaguerTypeEnum {
	ALL("全部"), 
	HIGH_GRADE_CUSTOMER("优质客"), 
	RISK_CUSTOMER("风险流失客"), 
	QUIESCENCE_CUSTOMER("静止客"),
	ATTENTION_CUSTOMER("标星客"),
	;

	private String mark;

	private LeaguerTypeEnum(String mark) {
		this.mark = mark;
	}

	public static LeaguerTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
