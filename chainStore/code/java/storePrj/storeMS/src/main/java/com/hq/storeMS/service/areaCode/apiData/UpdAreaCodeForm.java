package com.hq.storeMS.service.areaCode.apiData;

public class UpdAreaCodeForm {
	private int updateType;

	private UpdAreaCodeInfoForm updAreaCodeInfoForm;
	private UpdAreaCodeStatusForm updAreaCodeStatusForm;

	public static UpdAreaCodeForm newInstance() {
		return new UpdAreaCodeForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public UpdAreaCodeInfoForm getUpdAreaCodeInfoForm() {
		return updAreaCodeInfoForm;
	}

	public void setUpdAreaCodeInfoForm(UpdAreaCodeInfoForm updAreaCodeInfoForm) {
		this.updAreaCodeInfoForm = updAreaCodeInfoForm;
	}

	public UpdAreaCodeStatusForm getUpdAreaCodeStatusForm() {
		return updAreaCodeStatusForm;
	}

	public void setUpdAreaCodeStatusForm(
			UpdAreaCodeStatusForm updAreaCodeStatusForm) {
		this.updAreaCodeStatusForm = updAreaCodeStatusForm;
	}

}
