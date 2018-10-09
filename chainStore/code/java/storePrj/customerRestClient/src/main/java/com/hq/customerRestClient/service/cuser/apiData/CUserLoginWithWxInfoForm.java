package com.hq.customerRestClient.service.cuser.apiData;

public class CUserLoginWithWxInfoForm {
	private String jscode;
	private String encryptedData;
	private String iv;

	public static CUserLoginWithWxInfoForm newInstance() {
		CUserLoginWithWxInfoForm data = new CUserLoginWithWxInfoForm();
		return data;
	}

	public String getJscode() {
		return jscode;
	}

	public void setJscode(String jscode) {
		this.jscode = jscode;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}
}
