package com.hq.storeMS.service.storeProductInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;

public interface IProductHandler {
	public OperateTips update(StoreProductInfoUpdateForm formInfo);
}
