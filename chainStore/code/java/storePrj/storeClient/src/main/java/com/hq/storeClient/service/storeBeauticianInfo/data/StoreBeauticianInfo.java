package com.hq.storeClient.service.storeBeauticianInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeBeauticianInfo")
public class StoreBeauticianInfo {
	@Id
	private long id;
	private long storeId;
	
	// 服务人员列表 {buserId:BeauticianInfo}
	private Map<Long, BeauticianInfo> beauticianInfoMap = new HashMap<Long, BeauticianInfo>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreBeauticianInfo newInstance() {
		StoreBeauticianInfo storeBeauticianInfo = new StoreBeauticianInfo();
		return storeBeauticianInfo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
