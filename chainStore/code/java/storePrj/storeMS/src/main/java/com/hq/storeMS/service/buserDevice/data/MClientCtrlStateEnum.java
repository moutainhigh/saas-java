package com.hq.storeMS.service.buserDevice.data;


public enum MClientCtrlStateEnum {
	Poweroff("已关机"),
	Poweron("已开机"),
	RequestingData("请求数据中"),//已过时
	Standby("待机中"),
	Readying("准备中"),
	Working("工作中"),
	Locked("已锁定"),//已过时
	LockedBySupplier("被厂家锁定"),//已过时
	;

	private String mark;
	
	private MClientCtrlStateEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MClientCtrlStateEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
