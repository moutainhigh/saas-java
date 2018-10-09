package com.hq.storeMS.service.buserDevice.data;

public enum MClientLockStateEnum {
	unLocked("未锁定"),//已解锁
	Locked("已锁定"),
	LockedBySupplier("被厂家锁定"),
	;

	private String mark;
	
	private MClientLockStateEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MClientLockStateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}