package com.hq.storeMS.service.storeCardInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;

public interface ICardUpdateHandler {
	public OperateTips update(long storeId, StoreCardInfoUpdateApiForm updateForm);
}
