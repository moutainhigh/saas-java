package com.hq.storeMS.service.workFlowData.apiData.save;


public enum WorkFlowDataSaveTypeEnum {
	SAVE("保存"),
	ADDORDER("保存并生成订单"),
	;

	private String mark;

	private WorkFlowDataSaveTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static WorkFlowDataSaveTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
