package com.hq.storeMS.service.bonusRecord.data;

/**
 * 提成类型
 */
public enum BonusTypeEnum {
	FixedBonus("固定提成"), 
	PercentBonus("比例提成"), 
	;

	private String mark;

	private BonusTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static BonusTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
