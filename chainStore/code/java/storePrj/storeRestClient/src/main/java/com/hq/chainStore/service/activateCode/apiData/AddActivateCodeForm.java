package com.hq.chainStore.service.activateCode.apiData;

public class AddActivateCodeForm {
	private String activateCode;

	public static AddActivateCodeForm newInstance() {
		return new AddActivateCodeForm();
	}

	public String getActivateCode() {
		return activateCode;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}
}
