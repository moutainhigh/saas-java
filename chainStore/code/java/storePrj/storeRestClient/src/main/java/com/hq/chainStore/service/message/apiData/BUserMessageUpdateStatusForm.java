package com.hq.chainStore.service.message.apiData;

public class BUserMessageUpdateStatusForm {
	private int status;

	public static BUserMessageUpdateStatusForm newInstance() {
		return new BUserMessageUpdateStatusForm();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
