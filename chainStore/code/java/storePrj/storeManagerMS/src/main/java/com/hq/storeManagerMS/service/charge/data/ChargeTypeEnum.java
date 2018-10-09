package com.hq.storeManagerMS.service.charge.data;

public enum ChargeTypeEnum {
	NORMAL("普通"), 
	RENEW("续费"), 
	UPGRADE("升级"),
	;

	private String descript;

	private ChargeTypeEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static ChargeTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
