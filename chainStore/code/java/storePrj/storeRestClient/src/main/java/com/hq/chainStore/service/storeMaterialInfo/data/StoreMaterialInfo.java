package com.hq.chainStore.service.storeMaterialInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "storeMaterialInfo")
public class StoreMaterialInfo implements IntfSynData{

	@Id	
	private long id;
	
	private long storeId;
	
	private int materialIdIndex = 1;
	
	private Map<String,MaterialInfo> materialInfoMap = new HashMap<String,MaterialInfo>();
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	public static StoreMaterialInfo newInstance(long storeId){
		StoreMaterialInfo storeMaterialInfo = new StoreMaterialInfo();
		storeMaterialInfo.id = storeId;
		storeMaterialInfo.storeId = storeId;
		long curTime = System.currentTimeMillis();
		
		storeMaterialInfo.createdTime = curTime;
		storeMaterialInfo.lastUpdateTime = curTime;
		
		return storeMaterialInfo;
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

	public int getMaterialIdIndex() {
		return materialIdIndex;
	}

	public void setMaterialIdIndex(int materialIdIndex) {
		this.materialIdIndex = materialIdIndex;
	}

	public Map<String, MaterialInfo> getMaterialInfoMap() {
		return materialInfoMap;
	}

	public void setMaterialInfoMap(Map<String, MaterialInfo> materialInfoMap) {
		this.materialInfoMap = materialInfoMap;
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
	
	public void incrVer() {
		this.ver = ver+1;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}
	
}
