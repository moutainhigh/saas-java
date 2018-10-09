package com.hq.chainClient.service.chain.apiData;

import java.util.HashSet;
import java.util.Set;

public class ApplyChainBatchDoForm {
	private long chainId;
	private Set<Long> storeIds = new HashSet<Long>();
	// 审核的状态 对应 ApplyStatusEnum
	private int status;

	public static ApplyChainBatchDoForm newInstance() {
		return new ApplyChainBatchDoForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public Set<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(Set<Long> storeIds) {
		this.storeIds = storeIds;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
