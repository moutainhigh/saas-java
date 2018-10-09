package com.hq.chainStore.service.detailDataVersion.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "detailDataVersion")
public class DetailDataVersion implements IntfSynData {
	private long id;
	private long storeId;

	// {DataVersionEnum:version}
	private Map<Integer, Long> detailDataVerMap = new HashMap<Integer, Long>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static DetailDataVersion newInstance(){
		return new DetailDataVersion();
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Map<Integer, Long> getDetailDataVerMap() {
		return detailDataVerMap;
	}

	public void setDetailDataVerMap(Map<Integer, Long> detailDataVerMap) {
		this.detailDataVerMap = detailDataVerMap;
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
