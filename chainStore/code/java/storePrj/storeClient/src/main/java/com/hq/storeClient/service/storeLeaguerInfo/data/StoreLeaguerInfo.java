package com.hq.storeClient.service.storeLeaguerInfo.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeLeaguerInfo")
public class StoreLeaguerInfo {
	@Id
	private long id;

	private long storeId;
	private Map<String, Leaguer> leaguerInfoMap = new HashMap<String, Leaguer>();

	// 标签
	private long labelIndex = 0L;
	private Map<String, LeaguerLabel> leaguerLabelMap = new HashMap<String, LeaguerLabel>();

	// 数据拆分标识
	private int splitMark;
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreLeaguerInfo newInstance(long storeId) {
		StoreLeaguerInfo data = new StoreLeaguerInfo();
		return data;
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

	public long getLabelIndex() {
		return labelIndex;
	}

	public void setLabelIndex(long labelIndex) {
		this.labelIndex = labelIndex;
	}

	public Map<String, LeaguerLabel> getLeaguerLabelMap() {
		return leaguerLabelMap;
	}

	public void setLeaguerLabelMap(Map<String, LeaguerLabel> leaguerLabelMap) {
		this.leaguerLabelMap = leaguerLabelMap;
	}

}
