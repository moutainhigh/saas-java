package com.hq.storeMS.service.workFlowData.data;


public enum WorkFlowDataStatusEnum {
	OPEN("待提交"),
	COMPLETE("待收款"),
	CANCEL("已作废"),
	HASPAY("已完成"),
	;

	private String mark;

	private WorkFlowDataStatusEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static WorkFlowDataStatusEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
