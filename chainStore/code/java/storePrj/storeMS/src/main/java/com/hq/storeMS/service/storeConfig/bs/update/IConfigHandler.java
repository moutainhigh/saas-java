package com.hq.storeMS.service.storeConfig.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeConfig.apiData.StoreConfigUpdateForm;

public interface IConfigHandler {
	public OperateTips update(long storeId, StoreConfigUpdateForm formInfo);
}
