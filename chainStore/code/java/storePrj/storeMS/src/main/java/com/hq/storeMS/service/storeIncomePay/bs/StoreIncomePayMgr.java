package com.hq.storeMS.service.storeIncomePay.bs;

import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePay;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreIncomePayMgr {

	public static StoreIncomePayMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreIncomePayMgr.class);
	}

	public void addWithId(StoreIncomePay target) {
		StoreIncomePayDataHolder.getInstance().addWithId(target);
	}

	public void delete(StoreIncomePay target) {
		StoreIncomePayDataHolder.getInstance().delete(target);
	}

	public void update(StoreIncomePay target) {
		StoreIncomePayDataHolder.getInstance().update(target);
	}
	
	public StoreIncomePay getByStoreId(long storeId) {
		StoreIncomePay storeIncomePayInfo = StoreIncomePayDataHolder.getInstance().get(storeId);
		if(storeIncomePayInfo == null){//不存在，则新增
			storeIncomePayInfo = StoreIncomePay.newInstance(storeId);
			StoreIncomePayDataHolder.getInstance().addWithId(storeIncomePayInfo);
		}
		return storeIncomePayInfo;
	}
}
