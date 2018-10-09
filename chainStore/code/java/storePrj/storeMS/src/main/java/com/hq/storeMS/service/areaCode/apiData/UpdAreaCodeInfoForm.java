package com.hq.storeMS.service.areaCode.apiData;

public class UpdAreaCodeInfoForm {
	private String appName;// 应用名称
	private String areaCode;// 应用版本号
	private String appUrl;// 下载的链接
	private String descript;// 描述

	public static UpdAreaCodeInfoForm newInstance() {
		return new UpdAreaCodeInfoForm();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
