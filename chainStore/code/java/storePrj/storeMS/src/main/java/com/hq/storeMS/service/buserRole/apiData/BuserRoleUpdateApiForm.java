package com.hq.storeMS.service.buserRole.apiData;

public class BuserRoleUpdateApiForm {
	private int updateType;

	private BuserRoleUpdateInfoForm updateInfoData;

	public static BuserRoleUpdateApiForm newInstance() {
		return new BuserRoleUpdateApiForm();
	}

	public BuserRoleUpdateType getUpdateTypeEnum() {
		return BuserRoleUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public BuserRoleUpdateInfoForm getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(BuserRoleUpdateInfoForm updateInfoData) {
		this.updateInfoData = updateInfoData;
	}
}
