package com.hq.storeMS.service.packageProjectDetail.data;

public enum PackageItemEnum {
	PRODUCT("项目"), 
	GOODS("商品"), 
	;

	private String mark;

	private PackageItemEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static PackageItemEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
