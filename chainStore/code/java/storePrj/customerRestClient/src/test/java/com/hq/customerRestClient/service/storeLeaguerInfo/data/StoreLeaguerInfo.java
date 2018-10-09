package com.hq.customerRestClient.service.storeLeaguerInfo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "storeLeaguerInfo")
public class StoreLeaguerInfo {

	@Id
	private long id;

	private long storeId;
	private Map<String, Leaguer> leaguerInfoMap = new HashMap<String, Leaguer>();

	//数据拆分标识
	private int splitMark;
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreLeaguerInfo newInstance(long storeId) {
		StoreLeaguerInfo storeLeaguerInfo = new StoreLeaguerInfo();
		storeLeaguerInfo.id = storeId;
		storeLeaguerInfo.storeId = storeId;
		storeLeaguerInfo.splitMark = SplitMarkEnum.FINISH.ordinal();
		
		long curTime = System.currentTimeMillis();
		storeLeaguerInfo.createdTime = curTime;
		storeLeaguerInfo.lastUpdateTime = curTime;
		return storeLeaguerInfo;
	}
	
	public List<Leaguer> takeLeaguerInfoList(){
		List<Leaguer> list = new ArrayList<Leaguer>();
		if(!leaguerInfoMap.isEmpty()){
			list.addAll(leaguerInfoMap.values());
		}
		return list;
	}

	public Map<Long, Leaguer> takeLeaguerMapWithPhoneKey(){
		Map<Long, Leaguer> map = new HashMap<Long, Leaguer>();
		if(!leaguerInfoMap.isEmpty()){
			Collection<Leaguer> values = leaguerInfoMap.values();
			for (Leaguer leaguer : values) {
				map.put(leaguer.getPhone(), leaguer);
			}
		}
		return map;
	}

	public void incrVer() {
		this.ver = ver + 1;
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

	public Map<String, Leaguer> getLeaguerInfoMap() {
		return leaguerInfoMap;
	}

	public void setLeaguerInfoMap(Map<String, Leaguer> leaguerInfoMap) {
		this.leaguerInfoMap = leaguerInfoMap;
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

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
	}

}
