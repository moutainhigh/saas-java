package com.hq.chainStore.service.buser.apiData;


public class BUserUpdateApiForm {

	private int updateType;

	private BUserUpdateInfoApiData updateInfoData;

	private BUserChangePasswordApiData changePasswordData;

	private BUserUpdateVipTypeData updateVipTypeData;// 修改会员类型

	private BUserVipRechargeData vipRechargeData;// 会员充值

	public static BUserUpdateApiForm newInstance() {
		return new BUserUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public BUserUpdateInfoApiData getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(BUserUpdateInfoApiData updateInfoData) {
		this.updateInfoData = updateInfoData;
	}

	public BUserChangePasswordApiData getChangePasswordData() {
		return changePasswordData;
	}

	public void setChangePasswordData(
			BUserChangePasswordApiData changePasswordData) {
		this.changePasswordData = changePasswordData;
	}

	public BUserUpdateVipTypeData getUpdateVipTypeData() {
		return updateVipTypeData;
	}

	public void setUpdateVipTypeData(BUserUpdateVipTypeData updateVipTypeData) {
		this.updateVipTypeData = updateVipTypeData;
	}

	public BUserVipRechargeData getVipRechargeData() {
		return vipRechargeData;
	}

	public void setVipRechargeData(BUserVipRechargeData vipRechargeData) {
		this.vipRechargeData = vipRechargeData;
	}

}
