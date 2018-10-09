package com.hq.chainStore.service.arrearage.apiData;

public enum ArrearageUpdateType {
	AddPaymentHistory("新增还款记录"),
	;
	
	private String mark;
	
	private ArrearageUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static ArrearageUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
