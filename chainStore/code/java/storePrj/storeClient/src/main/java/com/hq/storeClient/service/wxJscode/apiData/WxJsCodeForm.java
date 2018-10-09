package com.hq.storeClient.service.wxJscode.apiData;

public class WxJsCodeForm {
	private String jscode;
	private String appId;

	public static WxJsCodeForm newInstance() {
		WxJsCodeForm data = new WxJsCodeForm();
		return data;
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
