package com.hq.chainStore.service.storeIncomePay.data;

import com.hq.common.dataSyn.bs.IntfSynData;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Table(name = "storeIncomePay")
public class StoreIncomePay implements IntfSynData {
	@Id
	private long id;
	private long storeId;
	//收支分类ID
	private long incomePayTypeIdIndex = 0L;
	//收支分类
	private Map<String, IncomePayType> incomePayTypeMap = new HashMap<String, IncomePayType>();
	//数据拆分标识
	private int splitMark;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static StoreIncomePay newInstance(long storeId) {
		StoreIncomePay data = new StoreIncomePay();
		return data;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	public long getIncomePayTypeIdIndex() {
		return incomePayTypeIdIndex;
	}

	public void setIncomePayTypeIdIndex(long incomePayTypeIdIndex) {
		this.incomePayTypeIdIndex = incomePayTypeIdIndex;
	}

	public Map<String, IncomePayType> getIncomePayTypeMap() {
		return incomePayTypeMap;
	}

	public void setIncomePayTypeMap(Map<String, IncomePayType> incomePayTypeMap) {
		this.incomePayTypeMap = incomePayTypeMap;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
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
