package com.hq.storeManagerMS.service.vipLevelType.apiData;

public class VipLevelTypeUpdateForm {

	private int updateType;

	private UpdateVipLevelTypeForm updateVipLevelTypeForm;
	private UpdateVipLevelTypeStateForm updateVipLevelTypeStateForm;
	private RemoveVipLevelTypeForm removeVipLevelTypeForm;

	public static VipLevelTypeUpdateForm newInstance() {
		return new VipLevelTypeUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public UpdateVipLevelTypeForm getUpdateVipLevelTypeForm() {
		return updateVipLevelTypeForm;
	}

	public void setUpdateVipLevelTypeForm(UpdateVipLevelTypeForm updateVipLevelTypeForm) {
		this.updateVipLevelTypeForm = updateVipLevelTypeForm;
	}

	public UpdateVipLevelTypeStateForm getUpdateVipLevelTypeStateForm() {
		return updateVipLevelTypeStateForm;
	}

	public void setUpdateVipLevelTypeStateForm(UpdateVipLevelTypeStateForm updateVipLevelTypeStateForm) {
		this.updateVipLevelTypeStateForm = updateVipLevelTypeStateForm;
	}

	public RemoveVipLevelTypeForm getRemoveVipLevelTypeForm() {
		return removeVipLevelTypeForm;
	}

	public void setRemoveVipLevelTypeForm(RemoveVipLevelTypeForm removeVipLevelTypeForm) {
		this.removeVipLevelTypeForm = removeVipLevelTypeForm;
	}

	

}
