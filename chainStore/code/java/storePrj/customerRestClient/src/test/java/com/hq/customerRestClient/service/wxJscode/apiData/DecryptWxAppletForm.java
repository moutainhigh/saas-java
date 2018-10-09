package com.hq.customerRestClient.service.wxJscode.apiData;

public class DecryptWxAppletForm {
	private String jscode;
	private String encryptedData;
	private String iv;
	private String appId;

	public static DecryptWxAppletForm newInstance() {
		DecryptWxAppletForm data = new DecryptWxAppletForm();
		return data;
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

	public String getJscode() {
		return jscode;
	}

	public void setJscode(String jscode) {
		this.jscode = jscode;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
