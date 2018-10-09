package com.hq.chainMS.service.chainClerk.apiData;

public class ChainClerkReomveForm {
	private long chainId;
	private long userId;

	public static ChainClerkReomveForm newInstance() {
		return new ChainClerkReomveForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
