package com.hq.storeManagerRestClient.service.muser.apiData;

public enum MUserUpdateType {
	updateInfo("修改用户信息"),
	changePassword("修改密码"),
	updRoleSet4Clerk("更新用户角色"),
	updMUserStatus("更新用户状态"),
	;
	
	private String descript;
	
	private MUserUpdateType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public static MUserUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
