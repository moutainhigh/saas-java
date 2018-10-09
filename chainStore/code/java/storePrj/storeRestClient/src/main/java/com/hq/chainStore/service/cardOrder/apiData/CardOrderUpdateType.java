package com.hq.chainStore.service.cardOrder.apiData;

public enum CardOrderUpdateType {
	CancelOrder("取消订单"),
	PayOrder("支付订单"),
	
	;
	
	private String mark;
	
	private CardOrderUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static CardOrderUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
