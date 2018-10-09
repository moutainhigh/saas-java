package com.hq.chainMS.service.chain.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ChainStore {
	private long storeId;
	
	private long createTime;

	public static ChainStore newInstance(Long storeIdP) {
		ChainStore data = new ChainStore();
		data.storeId = storeIdP;
		data.createTime = System.currentTimeMillis();
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
