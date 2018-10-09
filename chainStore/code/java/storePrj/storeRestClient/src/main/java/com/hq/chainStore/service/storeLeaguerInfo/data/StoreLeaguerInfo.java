package com.hq.chainStore.service.storeLeaguerInfo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "storeLeaguerInfo")
public class StoreLeaguerInfo implements IntfSynData {
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

	public List<Leaguer> getLeaguerInfoList() {
		List<Leaguer> list = new ArrayList<Leaguer>();
		if (!leaguerInfoMap.isEmpty()) {
			list.addAll(leaguerInfoMap.values());
		}
		return list;
	}
	
	/**
	 * 根据标签名 获取标签
	 * @param labels
	 * @return
	 */
	public Map<String, LeaguerLabel> takeLabelsByLableNames(List<String> labels){
		Map<String, LeaguerLabel> result = new HashMap<String, LeaguerLabel>();
		Collection<LeaguerLabel> values = leaguerLabelMap.values();
		if(!values.isEmpty() && !labels.isEmpty()) {
			for (LeaguerLabel leaguerLabel : values) {
				if(labels.contains(leaguerLabel.getName())) {
					result.put(leaguerLabel.getId(), leaguerLabel);
				}
			}
		}
		return result;
	}

	public void incrVer() {
		this.ver = ver + 1;
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
