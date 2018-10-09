package com.hq.chainStore.service.storeBeauticianInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;


@Table(name = "storeBeauticianInfo")
public class StoreBeauticianInfo implements IntfSynData{

	@Id	
	private long id;
	
	private long storeId;
	
	//服务人员列表  {buserId:BeauticianInfo}
	private Map<Long,BeauticianInfo> beauticianInfoMap = new HashMap<Long,BeauticianInfo>();
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private int ver;
	
	
	public static StoreBeauticianInfo New(long storeIdP){
		StoreBeauticianInfo beautician = new StoreBeauticianInfo();
		beautician.storeId = storeIdP;
		long curTime = System.currentTimeMillis();
		
		beautician.createdTime = curTime;
		beautician.lastUpdateTime = curTime;
		
		return beautician;
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

	public void incrVer() {
		this.ver = this.ver+1;
		
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}


	@Override
	public long targetVer() {
		return this.ver;
	}


	@Override
	public String toString() {
		return "Beautician [id=" + id + ", storeId="
				+ storeId + ", createdTime=" + createdTime + ", lastUpdateTime="
				+ lastUpdateTime + ", ver=" + ver + "]";
	}
	
	
}
