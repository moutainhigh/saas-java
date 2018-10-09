package com.hq.storeMS.service.areaCode.apiData;

public class UpdAreaCodeStatusForm {
	private int status;

	public static UpdAreaCodeStatusForm newInstance() {
		return new UpdAreaCodeStatusForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
