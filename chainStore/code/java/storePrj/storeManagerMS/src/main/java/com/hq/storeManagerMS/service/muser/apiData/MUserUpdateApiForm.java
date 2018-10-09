package com.hq.storeManagerMS.service.muser.apiData;

public class MUserUpdateApiForm {

	private int updateType;

	private MUserUpdateInfoApiData updateInfoData;

	private MUserChangePasswordApiData changePasswordData;

	private MUserUpdateRoleSetApiData updateRoleSetApiData;
	
	private MUserUpdateStatusApiData updateStatusApiData;

	public static MUserUpdateApiForm newInstance() {
		return new MUserUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public MUserUpdateInfoApiData getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(MUserUpdateInfoApiData updateInfoData) {
		this.updateInfoData = updateInfoData;
	}

	public MUserChangePasswordApiData getChangePasswordData() {
		return changePasswordData;
	}

	public void setChangePasswordData(
			MUserChangePasswordApiData changePasswordData) {
		this.changePasswordData = changePasswordData;
	}

	public MUserUpdateRoleSetApiData getUpdateRoleSetApiData() {
		return updateRoleSetApiData;
	}

	public void setUpdateRoleSetApiData(
			MUserUpdateRoleSetApiData updateRoleSetApiData) {
		this.updateRoleSetApiData = updateRoleSetApiData;
	}

	public MUserUpdateStatusApiData getUpdateStatusApiData() {
		return updateStatusApiData;
	}

	public void setUpdateStatusApiData(MUserUpdateStatusApiData updateStatusApiData) {
		this.updateStatusApiData = updateStatusApiData;
	}

}
