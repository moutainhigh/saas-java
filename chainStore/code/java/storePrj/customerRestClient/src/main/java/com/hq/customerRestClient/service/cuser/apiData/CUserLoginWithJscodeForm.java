package com.hq.customerRestClient.service.cuser.apiData;

public class CUserLoginWithJscodeForm {
	private String jscode;

	public static CUserLoginWithJscodeForm newInstance() {
		CUserLoginWithJscodeForm data = new CUserLoginWithJscodeForm();
		return data;
	}

	public String getJscode() {
		return jscode;
	}

	public void setJscode(String jscode) {
		this.jscode = jscode;
	}

}
