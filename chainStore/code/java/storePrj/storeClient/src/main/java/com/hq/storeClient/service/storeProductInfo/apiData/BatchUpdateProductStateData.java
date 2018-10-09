package com.hq.storeClient.service.storeProductInfo.apiData;

import java.util.HashSet;
import java.util.Set;

/**
 * 批量修改项目状态
 */
public class BatchUpdateProductStateData {
	private Set<String> prdIdSet = new HashSet<String>();// 项目id
	private long storeId;
	private int state;

	public static BatchUpdateProductStateData newInstance() {
		return new BatchUpdateProductStateData();
	}

	public Set<String> getPrdIdSet() {
		return prdIdSet;
	}

	public void setPrdIdSet(Set<String> prdIdSet) {
		this.prdIdSet = prdIdSet;
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
