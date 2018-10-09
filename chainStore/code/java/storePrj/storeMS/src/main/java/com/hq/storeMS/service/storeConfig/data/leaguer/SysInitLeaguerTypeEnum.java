package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum SysInitLeaguerTypeEnum {
	HIGH_GRADE_CUSTOMER(20, "优质客", 0), 
	RISK_CUSTOMER(20, "流失风险", 1), 
	QUIESCENCE_CUSTOMER(60, "静止客", 1),
	
	;

	private int consumeDates;
	private int compare;
	private String typeName;

	private SysInitLeaguerTypeEnum(int consumeDatesP, String typeNameP, int compareP) {
		this.typeName = typeNameP;
		this.consumeDates = consumeDatesP;
		this.compare = compareP;
	}

	public static SysInitLeaguerTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public int getConsumeDates() {
		return consumeDates;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getCompare() {
		return compare;
	}
}
