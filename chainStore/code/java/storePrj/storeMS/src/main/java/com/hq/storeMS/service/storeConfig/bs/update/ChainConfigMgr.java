package com.hq.storeMS.service.storeConfig.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeConfig.apiData.ShareDataForm;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.ChainConfig;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainConfigMgr {
	public static ChainConfigMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainConfigMgr.class);
	}

	public OperateTips saveShareData(long storeId, ShareDataForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreConfigUpdateType.SaveShareData.getDescript() + "失败");
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(storeId);
		ChainConfig chainConfig = storeConfig.getChainConfig();
		if(chainConfig == null) {
			chainConfig = ChainConfig.newInstance();
			storeConfig.setChainConfig(chainConfig);
		}
		chainConfig.addShareDataConfig(inputData.toShareDataConfig());
		StoreConfigMgr.getInstance().update(storeConfig);
		tips.setSuccess(true);
		return tips;
	}
}
