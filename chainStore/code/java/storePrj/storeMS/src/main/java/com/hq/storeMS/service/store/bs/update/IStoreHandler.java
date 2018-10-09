package com.hq.storeMS.service.store.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.store.apiData.StoreUpdateApiForm;

public interface IStoreHandler {
	public OperateTips update(StoreUpdateApiForm formInfo);
}
