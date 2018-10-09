package com.hq.chainStore.service.chainDataSyn.data;

import javax.persistence.Table;

@Table(name = "chainDataSyn")
public class ChainDataSyn {
	private long storeId;
	private long chainId;

	public static ChainDataSyn newInstance() {
		return new ChainDataSyn();
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
}
