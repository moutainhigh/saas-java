package com.hq.storeMS.service.storeClerkInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;

public interface IClerkInfoHandler {
	public OperateTips update(StoreClerkInfoUpdateForm formInfo);
}
