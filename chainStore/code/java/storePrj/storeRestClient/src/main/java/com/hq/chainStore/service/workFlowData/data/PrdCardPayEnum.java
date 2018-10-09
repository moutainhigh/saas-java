package com.hq.chainStore.service.workFlowData.data;

/**
 * 项目、商品、套餐的支付类型
 */
public enum PrdCardPayEnum {
	CashPay("现结"), 
	PrdCard("划卡"), 
	Donation("赠送"), 
	;

	private String mark;

	private PrdCardPayEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static PrdCardPayEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
