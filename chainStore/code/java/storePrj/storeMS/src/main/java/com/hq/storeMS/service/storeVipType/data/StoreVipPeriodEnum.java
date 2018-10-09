package com.hq.storeMS.service.storeVipType.data;

public enum StoreVipPeriodEnum {

	ONE_MONTH(1,"1个月"),
	;
	
	private int month;
	private String mark;
	
	private StoreVipPeriodEnum(int month,String mark){
		this.month = month;
		this.mark = mark;
	}

	public int getMonth() {
		return month;
	}

	public String getMark() {
		return mark;
	}
	
	public static StoreVipPeriodEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
