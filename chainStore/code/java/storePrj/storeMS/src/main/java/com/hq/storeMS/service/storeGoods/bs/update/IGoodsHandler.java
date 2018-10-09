package com.hq.storeMS.service.storeGoods.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateForm;

public interface IGoodsHandler {
	public OperateTips update(long storeId, StoreGoodsUpdateForm formInfo);
}
