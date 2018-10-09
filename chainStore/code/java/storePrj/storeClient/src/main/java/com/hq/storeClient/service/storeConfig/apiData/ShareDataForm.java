package com.hq.storeClient.service.storeConfig.apiData;

public class ShareDataForm {
	private long chainId;
	//ShareEnum
	private int shareFlag;

	public static ShareDataForm newInstance() {
		ShareDataForm data = new ShareDataForm();
		return data;
	}
	
	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public int getShareFlag() {
		return shareFlag;
	}

	public void setShareFlag(int shareFlag) {
		this.shareFlag = shareFlag;
	}

}
