package com.hq.chainClient.service.chainUser.apiData;

public class ChainUserUpdateForm {
	private int updateType;

	private ChainUserUpdateInfoForm chainUserUpdateInfoForm;
	private ChangePasswordForm changePasswordForm;

	public static ChainUserUpdateForm newInstance() {
		return new ChainUserUpdateForm();
	}
	
	public ChainUserUpdateType getChainUserUpdateType() {
		return ChainUserUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public ChainUserUpdateInfoForm getChainUserUpdateInfoForm() {
		return chainUserUpdateInfoForm;
	}

	public void setChainUserUpdateInfoForm(ChainUserUpdateInfoForm chainUserUpdateInfoForm) {
		this.chainUserUpdateInfoForm = chainUserUpdateInfoForm;
	}

	public ChangePasswordForm getChangePasswordForm() {
		return changePasswordForm;
	}

	public void setChangePasswordForm(ChangePasswordForm changePasswordForm) {
		this.changePasswordForm = changePasswordForm;
	}

}
