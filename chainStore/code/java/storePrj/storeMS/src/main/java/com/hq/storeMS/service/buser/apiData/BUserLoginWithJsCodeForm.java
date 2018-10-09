package com.hq.storeMS.service.buser.apiData;

public class BUserLoginWithJsCodeForm {
	private String jscode;

	public static BUserLoginWithJsCodeForm newInstance() {
		BUserLoginWithJsCodeForm data = new BUserLoginWithJsCodeForm();
		return data;
	}

	public String getJscode() {
		return jscode;
	}

	public void setJscode(String jscode) {
		this.jscode = jscode;
	}

}
