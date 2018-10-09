package com.hq.storeMS.service.storeCardInfo.apiData;

public class MemberCardPullForm {
	private long chainId;
	private String id;

	public static MemberCardPullForm newInstance() {
		return new MemberCardPullForm();
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
