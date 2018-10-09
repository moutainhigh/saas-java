package com.hq.chainMS.service.storeConfig.bs;

import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.List;

public class StoreConfigMgr {

    public static StoreConfigMgr getInstance() {
        return HotSwap.getInstance().getSingleton(StoreConfigMgr.class);
    }

    public StoreConfig getStoreConfig(long storeId) {
        return StoreConfigDataHolder.getInstance().getStoreConfig(storeId);
    }

    public List<StoreConfig> getStoreConfigs(String storeIds) {
        return StoreConfigDataHolder.getInstance().getStoreConfigs(storeIds);
    }
}
