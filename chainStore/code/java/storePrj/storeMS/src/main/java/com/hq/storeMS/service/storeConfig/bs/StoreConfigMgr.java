package com.hq.storeMS.service.storeConfig.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigMgr {

    public static StoreConfigMgr getInstance() {
        return HotSwap.getInstance().getSingleton(StoreConfigMgr.class);
    }

    public void addWithId(StoreConfig target) {
        StoreConfigDataHolder.getInstance().addWithId(target);
    }

    public void delete(StoreConfig target) {
        StoreConfigDataHolder.getInstance().delete(target);
    }

    public void update(StoreConfig target) {
        StoreConfigDataHolder.getInstance().update(target);
    }

    public StoreConfig getByStoreId(long storeId) {
        StoreConfig storeConfigInfo = StoreConfigDataHolder.getInstance().get(storeId);
        if (storeConfigInfo == null) {
            storeConfigInfo = StoreConfig.newInstance(storeId);
            StoreConfigDataHolder.getInstance().addWithId(storeConfigInfo);
        }
        return storeConfigInfo;
    }

    public List<StoreConfig> getListByStoreIds(String storeIds) {
    	List<StoreConfig> result = new ArrayList<StoreConfig>();
    	String[] ids = storeIds.split(ServerConstants.COMMA_SYMBOL);
    	for (String id : ids) {
    		if(StringUtils.isBlank(id)) {
    			continue;
    		}
    		StoreConfig storeConfig = StoreConfigDataHolder.getInstance().get(Long.valueOf(id));
    		if(storeConfig!=null) {
    			result.add(storeConfig);
    		}
		}
        return result;
    }
}
