package com.hq.storeMS.service.wxJsApiTicket.apiData;

public class GenJssdkSignatureApiForm {
	private String appId;
	private String pageUrl;
	
	public static GenJssdkSignatureApiForm newInstance() {
		return new GenJssdkSignatureApiForm();
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	
}
