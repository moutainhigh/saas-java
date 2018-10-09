package com.hq.chainStore.service.appVersion.apiData;

public class UpdAppVersionStatusForm {
	private int status;

	public static UpdAppVersionStatusForm newInstance() {
		return new UpdAppVersionStatusForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
