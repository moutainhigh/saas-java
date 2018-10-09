package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum SysInitLeaguerOriginEnum {
	PASS_STORE("路过进店"),
	OLD_WITH_NEW("老带新"),
	MEI_TUAN("美团"),
	;

	private String originName;

	private SysInitLeaguerOriginEnum(String originNameP) {
		this.originName = originNameP;
	}

	public static SysInitLeaguerOriginEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getOriginName() {
		return originName;
	}
}
