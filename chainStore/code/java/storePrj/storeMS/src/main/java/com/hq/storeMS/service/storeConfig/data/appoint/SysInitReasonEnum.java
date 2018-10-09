package com.hq.storeMS.service.storeConfig.data.appoint;

public enum SysInitReasonEnum {
	Other(1, "其他"),
	Redo(2, "重新预约"),
	Conflict(3, "预约冲突"),
	Personal(4, "顾客个人原因"),
	;

	private int index;
	private String reason;

	private SysInitReasonEnum(int indexP, String reasonP) {
		this.reason = reasonP;
		this.index = indexP;
	}

	public static SysInitReasonEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public int getIndex() {
		return index;
	}

	public String getReason() {
		return reason;
	}
}
