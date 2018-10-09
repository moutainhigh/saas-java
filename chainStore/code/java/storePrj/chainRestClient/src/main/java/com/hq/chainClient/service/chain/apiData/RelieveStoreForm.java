package com.hq.chainClient.service.chain.apiData;

public class RelieveStoreForm {
	private long chainId;
	private long storeId;

	// 操作来源 OperateFromEnum
	private int operateFrom;

	public static RelieveStoreForm newInstance() {
		return new RelieveStoreForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getOperateFrom() {
		return operateFrom;
	}

	public void setOperateFrom(int operateFrom) {
		this.operateFrom = operateFrom;
	}

}
