package com.hq.storeMS.service.dynamic.apiData;

public class DynamicUpdateApiForm {
	private int updateType;

	private DynamicUpdateInfoForm updateInfoForm;
	private DynamicUpdateStatusForm updateStatusForm;

	public static DynamicUpdateApiForm newInstance() {
		return new DynamicUpdateApiForm();
	}

	public DynamicUpdateType getDynamicUpdateType() {
		return DynamicUpdateType.valueOf(updateType);
	}

	public void setDynamicUpdateType(DynamicUpdateType dynamicUpdateType) {
		updateType = dynamicUpdateType.ordinal();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public DynamicUpdateInfoForm getUpdateInfoForm() {
		return updateInfoForm;
	}

	public void setUpdateInfoForm(DynamicUpdateInfoForm updateInfoForm) {
		this.updateInfoForm = updateInfoForm;
	}

	public DynamicUpdateStatusForm getUpdateStatusForm() {
		return updateStatusForm;
	}

	public void setUpdateStatusForm(DynamicUpdateStatusForm updateStatusForm) {
		this.updateStatusForm = updateStatusForm;
	}

}
