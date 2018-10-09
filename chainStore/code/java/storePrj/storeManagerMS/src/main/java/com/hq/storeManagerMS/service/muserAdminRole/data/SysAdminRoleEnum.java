package com.hq.storeManagerMS.service.muserAdminRole.data;

/**
 * 系统固有的角色
 */
public enum SysAdminRoleEnum {
	MNG_ADMIN("超级管理员"), 
	MNG_OPERATE("普通操作员"), 
	;

	private String mark;

	private SysAdminRoleEnum(String markP) {
		this.mark = markP;
	}

	public String getMark() {
		return mark;
	}

	public static SysAdminRoleEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
