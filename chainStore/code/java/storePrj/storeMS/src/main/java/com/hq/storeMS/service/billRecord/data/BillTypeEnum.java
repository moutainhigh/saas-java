package com.hq.storeMS.service.billRecord.data;

public enum BillTypeEnum {

	Income("收款"),
	Cost("付款"),
	;
	
	private String mark;
	
	private BillTypeEnum(String mark){
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static BillTypeEnum valueOf(int ordinal){
		if(ordinal < 0 || ordinal >= values().length){
			throw new IndexOutOfBoundsException("invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
