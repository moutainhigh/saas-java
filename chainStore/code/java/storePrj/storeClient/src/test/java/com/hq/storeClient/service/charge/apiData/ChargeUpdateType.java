package com.hq.storeClient.service.charge.apiData;

public enum ChargeUpdateType {
	UpdateInfo("修改收费基本信息"),
	UpdateStatus("修改收费状态信息"),
	;
	
	private String mark;
	
	private ChargeUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static ChargeUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
