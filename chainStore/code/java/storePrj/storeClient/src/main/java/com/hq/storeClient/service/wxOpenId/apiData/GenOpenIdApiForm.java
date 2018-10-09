package com.hq.storeClient.service.wxOpenId.apiData;


public class GenOpenIdApiForm {
	private String appId;
	private String jsCode;
	
	public static GenOpenIdApiForm newInstance() {
		return new GenOpenIdApiForm();
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	
}
