package com.hq.chainStore.service.storeConfig.data.leaguer;

public enum LeaguerAttributeStateEnum {
	Enable("启用"), 
	Disable("禁用"),
	
	;

	private String descript;

	private LeaguerAttributeStateEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static LeaguerAttributeStateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
