package com.hq.chainStore.service.opuser.apiData;

public class OPUserUpdateApiForm {
	
	private int updateType;
	
	private OPUserUpdateInfoApiData updateInfoData;
	
	private OPUserChangePasswordApiData changePasswordData;
	
	private OPUserUpdateRoleApiData updateRoleData;
	
	

	public static OPUserUpdateApiForm newInstance(){
		return new OPUserUpdateApiForm();
	}
	
	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	public void setUpdateTypeEnum(OPUserUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public OPUserUpdateInfoApiData getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(OPUserUpdateInfoApiData updateInfoData) {
		this.updateInfoData = updateInfoData;
	}

	public OPUserChangePasswordApiData getChangePasswordData() {
		return changePasswordData;
	}

	public void setChangePasswordData(OPUserChangePasswordApiData changePasswordData) {
		this.changePasswordData = changePasswordData;
	}

	public OPUserUpdateRoleApiData getUpdateRoleData() {
		return updateRoleData;
	}

	public void setUpdateRoleData(OPUserUpdateRoleApiData updateRoleData) {
		this.updateRoleData = updateRoleData;
	}


	
	
}
