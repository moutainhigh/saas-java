package com.hq.orderMS.service.store.bs;

import com.hq.orderMS.service.store.data.Store;
import com.hq.orderMS.service.store.data.StoreCacheDAO;
import com.hq.orderMS.service.store.data.StoreDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreDataHolder {
	
	public static StoreDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreDataHolder.class);
	}

	public Store get(long id) {
		Store target = StoreCacheDAO.getInstance().get(id);
		if(target == null){
			target = StoreDAO.getInstance().get(id);
			if(target != null){
				StoreCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
}
