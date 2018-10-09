package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.chain.ShareDataConfig;
import com.hq.storeMS.service.storeConfig.data.chain.ShareEnum;

public class ShareDataForm {
	private long chainId;
	//ShareEnum
	private int shareFlag;

	public static ShareDataForm newInstance() {
		ShareDataForm data = new ShareDataForm();
		return data;
	}
	
	public ShareDataConfig toShareDataConfig() {
		ShareDataConfig data = ShareDataConfig.newInstance(chainId, ShareEnum.valueOf(shareFlag));
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
