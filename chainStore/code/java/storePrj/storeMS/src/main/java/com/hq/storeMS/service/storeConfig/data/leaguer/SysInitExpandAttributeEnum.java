package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum SysInitExpandAttributeEnum {
	FOCUS_POSITION("注重部位"),
	SKIN_PROPERTIES("皮肤性质"),
	ANAPHYLAXIS("过敏体质"),
	;

	private String attributeName;

	private SysInitExpandAttributeEnum(String attributeNameP) {
		this.attributeName = attributeNameP;
	}

	public static SysInitExpandAttributeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getAttributeName() {
		return attributeName;
	}
}
