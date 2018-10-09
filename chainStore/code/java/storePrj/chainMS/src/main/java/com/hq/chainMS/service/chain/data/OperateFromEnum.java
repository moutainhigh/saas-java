package com.hq.chainMS.service.chain.data;

public enum OperateFromEnum {
	chainMS("chainMS"),
	storeMS("storeMS"),
	;

	private String descript;

	private OperateFromEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static OperateFromEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
