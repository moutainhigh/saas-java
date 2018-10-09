package com.hq.customerMS.common.validate.info;

/**
 * 用于分表查询的路由键ID信息
 * @author kevin
 *
 */
public class ValidateInfo {
	private long bossId;
	private long storeId;
	private int appId;

	public static ValidateInfo newInstance() {
		ValidateInfo data = new ValidateInfo();
		return data;
	}
	
	public static ValidateInfo newInstance(long bossIdP, long storeIdP) {
		ValidateInfo data = new ValidateInfo();
		data.bossId = bossIdP;
		data.storeId = storeIdP;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
}