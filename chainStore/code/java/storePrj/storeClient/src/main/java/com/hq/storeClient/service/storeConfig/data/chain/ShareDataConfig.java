package com.hq.storeClient.service.storeConfig.data.chain;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ShareDataConfig {
	private long chainId;
	//ShareEnum
	private int shareFlag;

	public static ShareDataConfig newInstance() {
		ShareDataConfig data = new ShareDataConfig();
		return data;
	}
	
	public static ShareDataConfig newInstance(long chainIdP, ShareEnum shareEnum) {
		ShareDataConfig data = newInstance();
		data.chainId=chainIdP;
		data.shareFlag=shareEnum.ordinal();
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
