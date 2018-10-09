package com.hq.customerMS.service.cuser.apiData;

public class CUserUpdateApiForm {

	private int updateType;

	private CUserUpdateInfoApiData updateInfoData;

	private CUserChangePasswordApiData changePasswordData;
	
	private CUserAddressAddForm addressAddData;
	
	private CUserAddressUpdateForm addressUpdateData;
	
	private CUserAddressRemoveForm addressRemoveData;
	
	private CUserChangeDefaultAddressForm changeDefaultAddressData;

	public static CUserUpdateApiForm newInstance() {
		return new CUserUpdateApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public CUserUpdateInfoApiData getUpdateInfoData() {
		return updateInfoData;
	}

	public void setUpdateInfoData(CUserUpdateInfoApiData updateInfoData) {
		this.updateInfoData = updateInfoData;
	}

	public CUserChangePasswordApiData getChangePasswordData() {
		return changePasswordData;
	}

	public void setChangePasswordData(
			CUserChangePasswordApiData changePasswordData) {
		this.changePasswordData = changePasswordData;
	}

	public CUserAddressAddForm getAddressAddData() {
		return addressAddData;
	}

	public void setAddressAddData(CUserAddressAddForm addressAddData) {
		this.addressAddData = addressAddData;
	}

	public CUserAddressUpdateForm getAddressUpdateData() {
		return addressUpdateData;
	}

	public void setAddressUpdateData(CUserAddressUpdateForm addressUpdateData) {
		this.addressUpdateData = addressUpdateData;
	}

	public CUserAddressRemoveForm getAddressRemoveData() {
		return addressRemoveData;
	}

	public void setAddressRemoveData(CUserAddressRemoveForm addressRemoveData) {
		this.addressRemoveData = addressRemoveData;
	}

	public CUserChangeDefaultAddressForm getChangeDefaultAddressData() {
		return changeDefaultAddressData;
	}

	public void setChangeDefaultAddressData(CUserChangeDefaultAddressForm changeDefaultAddressData) {
		this.changeDefaultAddressData = changeDefaultAddressData;
	}
	
}
