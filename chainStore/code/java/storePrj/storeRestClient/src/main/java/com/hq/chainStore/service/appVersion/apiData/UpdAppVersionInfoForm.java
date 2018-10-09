package com.hq.chainStore.service.appVersion.apiData;

public class UpdAppVersionInfoForm {
	private String appName;// 应用名称
	private String appVersion;// 应用版本号
	private String appUrl;// 下载的链接
	private String descript;// 描述

	public static UpdAppVersionInfoForm newInstance() {
		return new UpdAppVersionInfoForm();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
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
