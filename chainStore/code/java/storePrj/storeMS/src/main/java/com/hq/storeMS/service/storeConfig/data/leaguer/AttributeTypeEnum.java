package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum AttributeTypeEnum {
	SingleLine("单行"), 
	MultiLine("多行"),
	
	;

	private String descript;

	private AttributeTypeEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static AttributeTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
