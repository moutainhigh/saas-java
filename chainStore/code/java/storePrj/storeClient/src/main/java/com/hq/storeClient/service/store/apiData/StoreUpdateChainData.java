package com.hq.storeClient.service.store.apiData;

import com.hq.storeClient.service.store.data.StoreOperateEnum;

public class StoreUpdateChainData {
	private long storeId;
	private long chainId;
	// 操作 StoreOperateEnum
	private int operate;

	public static StoreUpdateChainData newInstance() {
		return new StoreUpdateChainData();
	}
	
	public static StoreUpdateChainData newInstance(long storeIdP, long chainIdP, StoreOperateEnum storeOperateEnum) {
		StoreUpdateChainData data = new StoreUpdateChainData();
		data.storeId = storeIdP;
		data.chainId = chainIdP;
		data.operate = storeOperateEnum.ordinal();
		return data;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public int getOperate() {
		return operate;
	}

	public void setOperate(int operate) {
		this.operate = operate;
	}

}
