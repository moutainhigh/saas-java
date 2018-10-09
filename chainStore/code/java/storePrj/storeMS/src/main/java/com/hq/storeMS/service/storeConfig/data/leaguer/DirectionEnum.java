package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum DirectionEnum {
	UP("上移"), 
	DOWN("下移"),
	
	;

	private String descript;

	private DirectionEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static DirectionEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
