package com.hq.chainStore.service.appVersion.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "appVersion")
public class AppVersion implements IntfSynData {
	@Id
	private long id;// 主键ID

	private String appName;// 应用名称

	private String appVersion;// 应用版本号

	private String appUrl;// 下载的链接

	private String descript;// 描述

	private int status;// 状态 0:失效 1有效 AppVersionEnum

	private long createdTime;

	private long lastUpdateTime;

	private long ver;

	public static AppVersion newInstance() {
		return new AppVersion();
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

}
