package com.hq.storeManagerRestClient.service.vipLevel.apiData;

public class VipLevelUpdateForm {

	private int updateType;

	private UpdateVipLevelForm updateVipLevelForm;
	private UpdateVipLevelStateForm updateVipLevelStateForm;

	public static VipLevelUpdateForm newInstance() {
		return new VipLevelUpdateForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public VipLevelUpdateType getVipLevelUpdateType() {
		return VipLevelUpdateType.valueOf(updateType);
	}

	public void setUpdateTypeEnum(VipLevelUpdateType updateType) {
		this.updateType = updateType.ordinal();
	}

	public UpdateVipLevelForm getUpdateVipLevelForm() {
		return updateVipLevelForm;
	}

	public void setUpdateVipLevelForm(UpdateVipLevelForm updateVipLevelForm) {
		this.updateVipLevelForm = updateVipLevelForm;
	}

	public UpdateVipLevelStateForm getUpdateVipLevelStateForm() {
		return updateVipLevelStateForm;
	}

	public void setUpdateVipLevelStateForm(UpdateVipLevelStateForm updateVipLevelStateForm) {
		this.updateVipLevelStateForm = updateVipLevelStateForm;
	}

}
