package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum SysInitBirthdayEnum {
	ZeroDay(0, "当天"), 
	OneDay(1, "1天"),
	TwoDay(2, "2天"),
	SevenDay(7, "7天"),
	FifteenDay(15, "15天"),
	ThirtyDay(30, "30天"),
	;

	private int count;
	private String mark;

	private SysInitBirthdayEnum(int count, String mark) {
		this.count = count;
		this.mark = mark;
	}

	public static SysInitBirthdayEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public int getCount() {
		return count;
	}

	public String getMark() {
		return mark;
	}
}
