package com.hq.chainMS.service.chain.apiData;

public class ApplyChainDoForm {
	private long chainId;
	private long storeId;
	// 审核的状态 对应 ApplyStatusEnum
	private int status;

	public static ApplyChainDoForm newInstance() {
		return new ApplyChainDoForm();
	}
	
	public static ApplyChainDoForm newInstance(long chainId, long storeId, int status) {
		ApplyChainDoForm data = new ApplyChainDoForm();
		data.chainId = chainId;
		data.storeId = storeId;
		data.status = status;
		return data;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
