package com.hq.storeMS.service.storeClerkInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.apiData.SetMonthPayDaysData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkMonthPayMgr {
	
	public static StoreClerkMonthPayMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkMonthPayMgr.class);
	}
	
	//设置店铺工资月结天数
	public OperateTips setMonthPayDays(SetMonthPayDaysData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.SetMonthPayDays.getDescript()+"失败");
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		storeClerkInfo.setMonthPayDays(inputData.getMonthPayDays());
		StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
		tips.setSuccess(true);
		return tips;
	}
	
	
}
