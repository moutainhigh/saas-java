package com.hq.orderRestClient.service.order.data;

public enum OrderDataTypeEnum {
	NORMAL_RCD("普通开单"),
	OLD_RCD("补录开单"),
	;
	
	
	private String mark;
	
	private OrderDataTypeEnum(String mark){
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

	public static OrderDataTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

}
