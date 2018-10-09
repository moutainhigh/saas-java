package com.hq.chainStore.service.storeCardInfo.apiData;

import java.util.HashSet;
import java.util.Set;

/**
 * 批量修改次卡状态
 */
public class BatchUpdProductCardStateData {
	private Set<String> prdCardIdSet = new HashSet<String>();// 次卡id
	private long storeId;
	private int state;

	public static BatchUpdProductCardStateData newInstance() {
		return new BatchUpdProductCardStateData();
	}

	public Set<String> getPrdCardIdSet() {
		return prdCardIdSet;
	}

	public void setPrdCardIdSet(Set<String> prdCardIdSet) {
		this.prdCardIdSet = prdCardIdSet;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
