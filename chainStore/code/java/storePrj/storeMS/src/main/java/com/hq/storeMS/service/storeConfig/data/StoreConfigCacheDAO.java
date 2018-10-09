package com.hq.storeMS.service.storeConfig.data;

import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigCacheDAO {

    public static StoreConfigCacheDAO getInstance() {
        return HotSwap.getInstance().getSingleton(StoreConfigCacheDAO.class);
    }

    public void save(StoreConfig target) {
        StoreConfigRedisDAO.getInstance().save(target);
    }

    public StoreConfig get(long id) {
        return StoreConfigRedisDAO.getInstance().get(id);
    }

    public void delete(StoreConfig target) {
        StoreConfigRedisDAO.getInstance().delete(target.getId());
    }
}
