package com.hq.storeMS.service.footprint.bs;

import com.hq.storeMS.service.dynamic.bs.DynamicQueryMgr;
import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.hq.storeMS.service.footprint.apiData.FootprintAddForm;
import com.hq.storeMS.service.footprint.data.Footprint;
import com.zenmind.common.hotSwap.HotSwap;

public class FootprintModifyMgr {

	public static FootprintModifyMgr getInstance() {
		return HotSwap.getInstance().getSingleton(FootprintModifyMgr.class);
	}
	
	public void addAndReturnId(Footprint target) {
		FootprintDataHolder.getInstance().addAndReturnId(target);
	}

	public void update(Footprint target) {
		FootprintDataHolder.getInstance().update(target);
	}

	public Footprint addByForm(FootprintAddForm inputForm) {
		Footprint target = inputForm.toFootprint();
		Dynamic dynamic = DynamicQueryMgr.getInstance().get(inputForm.getDynamicId());
		target.setDynamicType(dynamic.getType());
		target.setBuserId(dynamic.getBuserId());
		addAndReturnId(target);
		return target;
	}
}
