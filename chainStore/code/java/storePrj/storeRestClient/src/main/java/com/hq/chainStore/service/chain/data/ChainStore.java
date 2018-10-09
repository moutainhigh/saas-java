package com.hq.chainStore.service.chain.data;

public class ChainStore {
	private long storeId;
	
	private long createTime;

	public static ChainStore newInstance() {
		ChainStore data = new ChainStore();
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
