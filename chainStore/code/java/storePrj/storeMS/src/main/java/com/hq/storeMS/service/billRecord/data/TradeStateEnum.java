package com.hq.storeMS.service.billRecord.data;

public enum TradeStateEnum {

	Failed("支付失败"),
	Success("支付成功"),
	;
	
	private String mark;
	
	private TradeStateEnum(String mark){
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static TradeStateEnum valueOf(int ordinal){
		if(ordinal < 0 || ordinal >= values().length){
			throw new IndexOutOfBoundsException("invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
