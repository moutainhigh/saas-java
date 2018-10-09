package com.hq.storeMS.service.buser.apiData;

public class BUserUpdateApiForm {
	
	private int updateType;
	
	private BUserUpdateInfoApiData updateInfoData;
	private BUserChangePasswordApiData changePasswordData;
	private BUserUpdateVipTypeData updateVipTypeData;
	private BUserVipRechargeData vipRechargeData;
	private BUserUnBindingWeChatForm unBindingWeChatForm;
	private BUserBindingWeChatForm bindingWeChatForm;

	public static BUserUpdateApiForm newInstance(){
		return new BUserUpdateApiForm();
	}
	
	public BUserUpdateType getBUserUpdateType() {
		return BUserUpdateType.valueOf(updateType);
	}
	
	public void setBUserUpdateType(BUserUpdateType bUserUpdateType) {
		updateType = bUserUpdateType.ordinal();
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

	public void setChangePasswordData(BUserChangePasswordApiData changePasswordData) {
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

	public BUserUnBindingWeChatForm getUnBindingWeChatForm() {
		return unBindingWeChatForm;
	}

	public void setUnBindingWeChatForm(BUserUnBindingWeChatForm unBindingWeChatForm) {
		this.unBindingWeChatForm = unBindingWeChatForm;
	}

	public BUserBindingWeChatForm getBindingWeChatForm() {
		return bindingWeChatForm;
	}

	public void setBindingWeChatForm(BUserBindingWeChatForm bindingWeChatForm) {
		this.bindingWeChatForm = bindingWeChatForm;
	}

}
