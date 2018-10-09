package com.hq.storeManagerMS.service.muserAdminRole.data;

/**
 * 权限枚举
 */
public enum MUserAdminPermEnum {
	MNG_ADMIN("mng:*:*:*", "超级管理员"), 
	MNG_MUSER("mng:muser:*:*", "用户管理"), 
	MNG_FEES("mng:fees:*:*", "收费管理"), 
	MNG_FUNC("mng:func:*:*", "功能方案"), 
	MNG_PROXY("mng:proxy:*:*", "代理管理"), 
	MNG_ACCOUNT("mng:account:*:*", "账号管理"), 
	MNG_VIPLEVEL("mng:vipLevel:*:*", "等级管理"), 

	;

	private String perm;

	// 权限描述
	private String descript;

	private MUserAdminPermEnum(String permP, String descriptP) {
		this.perm = permP;
		this.descript = descriptP;
	}

	public String getPerm() {
		return perm;
	}

	public String getDescript() {
		return descript;
	}

	public static MUserAdminPermEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
