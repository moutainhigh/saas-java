package com.hq.chainStore.service.workFlowType.data;


public enum SysFixCompEnum {
	BuyComp("购买消费"),
	MemCardComp("会员充值"),
	;

	private String mark;

	private SysFixCompEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static SysFixCompEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
