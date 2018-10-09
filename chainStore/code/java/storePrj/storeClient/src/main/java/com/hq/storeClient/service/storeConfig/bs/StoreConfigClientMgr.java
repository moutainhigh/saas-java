package com.hq.storeClient.service.storeConfig.bs;

import com.hq.storeClient.service.storeConfig.apiData.StoreConfigUpdateForm;
import com.hq.storeClient.service.storeConfig.data.StoreConfig;
import com.hq.storeClient.service.storeConfig.data.StoreConfigDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

import java.util.List;

public class StoreConfigClientMgr {

    public static StoreConfigClientMgr getInstance() {
        return HotSwap.getInstance().getSingleton(StoreConfigClientMgr.class);
    }

    public StoreConfig findStoreConfigByStoreId(long storeId) {
        return StoreConfigDAO.getInstance().get(storeId);
    }

    public void update(long storeId, StoreConfigUpdateForm updateForm) {
        StoreConfigDAO.getInstance().update(storeId, updateForm);
    }

    public List<StoreConfig> getStoreConfigs(String storeIds) {
    	final String findPath = "getStoreConfigs";
        ReqMap reqMap = new ReqMap();
        reqMap.add("storeIds", storeIds);
        return StoreConfigDAO.getInstance().findWithReqParam(findPath, reqMap, 0, 0);
    }

}
