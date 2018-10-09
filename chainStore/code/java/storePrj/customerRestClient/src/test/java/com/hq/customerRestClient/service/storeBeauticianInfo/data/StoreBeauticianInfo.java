package com.hq.customerRestClient.service.storeBeauticianInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "storeBeauticianInfo")
public class StoreBeauticianInfo {

	@Id
	private long id;

	private long storeId;

	//服务人员列表  {buserId:BeauticianInfo}
	private Map<Long, BeauticianInfo> beauticianInfoMap = new HashMap<Long, BeauticianInfo>();

	private long createdTime;

	private long lastUpdateTime;

	private int ver;

	public static StoreBeauticianInfo newInstance() {
		StoreBeauticianInfo data = new StoreBeauticianInfo();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Map<Long, BeauticianInfo> getBeauticianInfoMap() {
		return beauticianInfoMap;
	}

	public void setBeauticianInfoMap(Map<Long, BeauticianInfo> beauticianInfoMap) {
		this.beauticianInfoMap = beauticianInfoMap;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}
}
