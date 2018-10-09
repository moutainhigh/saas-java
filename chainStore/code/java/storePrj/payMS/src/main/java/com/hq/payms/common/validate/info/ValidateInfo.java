package com.hq.payms.common.validate.info;

public class ValidateInfo {
	private long bossId;
	private int appId;

	public static ValidateInfo newInstance() {
		ValidateInfo data = new ValidateInfo();
		return data;
	}
	
	public long getBossId() {
		return bossId;
	}

	public void setBossId(long bossId) {
		this.bossId = bossId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}
}
