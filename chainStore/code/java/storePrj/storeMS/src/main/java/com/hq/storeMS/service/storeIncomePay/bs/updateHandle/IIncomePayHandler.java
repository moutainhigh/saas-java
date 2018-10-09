package com.hq.storeMS.service.storeIncomePay.bs.updateHandle;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateForm;

public interface IIncomePayHandler {
	public OperateTips update(long storeId, StoreIncomePayUpdateForm formInfo);
}
