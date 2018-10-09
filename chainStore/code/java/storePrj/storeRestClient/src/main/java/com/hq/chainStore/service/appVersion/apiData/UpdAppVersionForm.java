package com.hq.chainStore.service.appVersion.apiData;

public class UpdAppVersionForm {
	private int updateType;

	private UpdAppVersionInfoForm updAppVersionInfoForm;
	private UpdAppVersionStatusForm updAppVersionStatusForm;

	public static UpdAppVersionForm newInstance() {
		return new UpdAppVersionForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public UpdAppVersionInfoForm getUpdAppVersionInfoForm() {
		return updAppVersionInfoForm;
	}

	public void setUpdAppVersionInfoForm(
			UpdAppVersionInfoForm updAppVersionInfoForm) {
		this.updAppVersionInfoForm = updAppVersionInfoForm;
	}

	public UpdAppVersionStatusForm getUpdAppVersionStatusForm() {
		return updAppVersionStatusForm;
	}

	public void setUpdAppVersionStatusForm(
			UpdAppVersionStatusForm updAppVersionStatusForm) {
		this.updAppVersionStatusForm = updAppVersionStatusForm;
	}

}
