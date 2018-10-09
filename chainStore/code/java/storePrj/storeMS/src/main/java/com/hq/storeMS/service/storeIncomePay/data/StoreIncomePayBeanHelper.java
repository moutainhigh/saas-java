package com.hq.storeMS.service.storeIncomePay.data;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storeIncomePay.apiData.IncomePayTypeAddForm;
import com.hq.storeMS.service.storeIncomePay.apiData.IncomePayTypeRemoveForm;
import com.hq.storeMS.service.storeIncomePay.apiData.IncomePayTypeUpdateForm;
import com.zenmind.common.hotSwap.HotSwap;

import java.util.Map;

public class StoreIncomePayBeanHelper {

	public static StoreIncomePayBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StoreIncomePayBeanHelper.class);
	}

	/*************************** 收支分类基本操作 ***************************/
	public boolean addIncomePayType(StoreIncomePay storeIncomePay, IncomePayTypeAddForm addForm) {
		if (storeIncomePay == null) {
			return false;
		}
		boolean success = false;
		IncomePayType data = addForm.toIncomePayType();
		long index = Long.valueOf(data.getId());
		Map<String, IncomePayType> incomePayTypeMap = storeIncomePay.getIncomePayTypeMap();
		if (!incomePayTypeMap.containsKey(data.getId()) && storeIncomePay.getIncomePayTypeIdIndex() + 1 == index) {
			incomePayTypeMap.put(data.getId(), data);
			storeIncomePay.setIncomePayTypeIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeIncomePayType(StoreIncomePay storeIncomePay, IncomePayTypeRemoveForm removeForm) {
		if (storeIncomePay == null) {
			return false;
		}
		boolean success = false;
		Map<String, IncomePayType> incomePayTypeMap = storeIncomePay.getIncomePayTypeMap();
		if (incomePayTypeMap.containsKey(removeForm.getIncomePayTypeId())) {
			IncomePayType incomePayType = incomePayTypeMap.get(removeForm.getIncomePayTypeId());
			incomePayType.setEntityState(EntityState.Deleted.ordinal());
			incomePayType.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

	public boolean updateIncomePayType(StoreIncomePay storeIncomePay, IncomePayTypeUpdateForm data) {
		if (storeIncomePay == null) {
			return false;
		}
		boolean success = false;
		Map<String, IncomePayType> incomePayTypeMap = storeIncomePay.getIncomePayTypeMap();
		if (incomePayTypeMap.containsKey(data.getId())) {
			IncomePayType incomePayType = incomePayTypeMap.get(data.getId());
			data.toIncomePayType(incomePayType);
			incomePayType.setLastUpdateTime(System.currentTimeMillis());
			incomePayTypeMap.put(incomePayType.getId(), incomePayType);
			success = true;
		}
		return success;
	}


}
