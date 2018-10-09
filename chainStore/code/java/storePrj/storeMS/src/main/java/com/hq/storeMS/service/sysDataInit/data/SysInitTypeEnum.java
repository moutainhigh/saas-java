package com.hq.storeMS.service.sysDataInit.data;

public enum SysInitTypeEnum {
	UnClassify("0", "未分类"),
	
	;

	private String id;
	private String name;

	private SysInitTypeEnum(String idP, String nameP) {
		this.id = idP;
		this.name = nameP;
	}

	public static SysInitTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
