package com.hq.storeMS.service.detailDataVersion.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "detailDataVersion")
public class DetailDataVersion {
	@Id	
	private long id;
	private long storeId;
	
	//{DataVersionEnum:version}
	private Map<Integer, Long> detailDataVerMap = new HashMap<Integer, Long>();
	
	private long createdTime;
	private long lastUpdateTime;
	private long ver;
	
	public static DetailDataVersion newInstance(long storeId){
		DetailDataVersion detailDataVersion = new DetailDataVersion();
		detailDataVersion.id = storeId;
		detailDataVersion.storeId = storeId;
		
		long curTime = System.currentTimeMillis();
		detailDataVersion.createdTime = curTime;
		detailDataVersion.lastUpdateTime = curTime;
		return detailDataVersion;
	}
	
	public void incrVer() {
		this.ver = ver+1;
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

	public Map<Integer, Long> getDetailDataVerMap() {
		return detailDataVerMap;
	}

	public void setDetailDataVerMap(Map<Integer, Long> detailDataVerMap) {
		this.detailDataVerMap = detailDataVerMap;
	}
	
	
}
