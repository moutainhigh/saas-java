package com.hq.storeMS.service.storeProductInfo.apiData;

import java.util.HashSet;
import java.util.Set;

/**
 * 批量修改项目分类的状态
 */
public class BatchUpdateProductTypeStateData {
	private Set<String> prdTypeIdSet = new HashSet<String>();// 项目id
	private long storeId;
	private int state;

	public static BatchUpdateProductTypeStateData newInstance() {
		return new BatchUpdateProductTypeStateData();
	}

	public Set<String> getPrdTypeIdSet() {
		return prdTypeIdSet;
	}

	public void setPrdTypeIdSet(Set<String> prdTypeIdSet) {
		this.prdTypeIdSet = prdTypeIdSet;
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
