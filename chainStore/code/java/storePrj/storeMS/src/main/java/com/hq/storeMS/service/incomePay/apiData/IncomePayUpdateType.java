package com.hq.storeMS.service.incomePay.apiData;

public enum IncomePayUpdateType {
	UpdateInfo("修改收入支出基本信息"),
	
	;
	
	private String mark;
	
	private IncomePayUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static IncomePayUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
