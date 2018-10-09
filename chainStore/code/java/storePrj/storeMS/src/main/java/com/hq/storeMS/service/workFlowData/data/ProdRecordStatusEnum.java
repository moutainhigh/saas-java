package com.hq.storeMS.service.workFlowData.data;


public enum ProdRecordStatusEnum {
	INCOMPLETE("未完成"),
	COMPLETE("已完成"),
	;

	private String mark;

	private ProdRecordStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static ProdRecordStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
