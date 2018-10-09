package com.hq.chainStore.service.serverConfig.apiData;

import com.zenmind.dao.rest.ReqMap;

public class ServerConfigQueryForm {
	// 应用名称 中文
	private String appNameCh;
	// 应用名称 英文
	private String appNameEn;
	// 应用版本
	private String appVersion;

	private int pageItemCount;
	private int pageNo;

	public static ServerConfigQueryForm newInstance() {
		ServerConfigQueryForm params = new ServerConfigQueryForm();
		params.appNameCh = "";
		params.appNameEn = "";
		params.appVersion = "";

		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("appNameCh", this.appNameCh)
				.add("appNameEn", this.appNameEn)
				.add("appVersion", this.appVersion);
		return reqMap;
	}

	public String getAppNameCh() {
		return appNameCh;
	}

	public void setAppNameCh(String appNameCh) {
		this.appNameCh = appNameCh;
	}

	public String getAppNameEn() {
		return appNameEn;
	}

	public void setAppNameEn(String appNameEn) {
		this.appNameEn = appNameEn;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
