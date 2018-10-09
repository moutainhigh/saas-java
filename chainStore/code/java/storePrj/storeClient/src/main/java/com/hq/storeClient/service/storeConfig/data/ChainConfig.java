package com.hq.storeClient.service.storeConfig.data;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeClient.service.storeConfig.data.chain.ShareDataConfig;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ChainConfig {
	private Map<Long, ShareDataConfig> shareDataConfigMap = new HashMap<Long, ShareDataConfig>();

	public static ChainConfig newInstance() {
		ChainConfig data = new ChainConfig();
		return data;
	}
	
	public ShareDataConfig takeShareDataConfigByChainId(Long chainId) {
		return shareDataConfigMap.get(chainId);
	}
	
	public void addShareDataConfig(ShareDataConfig dataConfig) {
		shareDataConfigMap.put(dataConfig.getChainId(), dataConfig);
	}

	public Map<Long, ShareDataConfig> getShareDataConfigMap() {
		return shareDataConfigMap;
	}

	public void setShareDataConfigMap(Map<Long, ShareDataConfig> shareDataConfigMap) {
		this.shareDataConfigMap = shareDataConfigMap;
	}

}
