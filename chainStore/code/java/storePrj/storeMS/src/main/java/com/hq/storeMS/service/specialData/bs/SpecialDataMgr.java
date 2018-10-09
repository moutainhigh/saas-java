package com.hq.storeMS.service.specialData.bs;

import com.hq.storeMS.service.specialData.data.SpecialData;
import com.zenmind.common.hotSwap.HotSwap;

public class SpecialDataMgr {

	public static SpecialDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataMgr.class);
	}

	public void addWithId(SpecialData target) {
		SpecialDataDataHolder.getInstance().addWithId(target);
	}

	public void update(SpecialData target) {
		SpecialDataDataHolder.getInstance().updpate(target);
	}

	public SpecialData get(String id) {
		SpecialData specialData = SpecialDataDataHolder.getInstance().get(id);
		if (specialData == null) {//如果为空，则生成一个默认对象
			specialData = SpecialData.newInstance();
			String ids[] = id.split("_");
			specialData.setId(id);
			specialData.setStoreId(Long.valueOf(ids[0]));
			specialData.setBuserId(Long.valueOf(ids[1]));
			SpecialDataDataHolder.getInstance().addWithId(specialData);
		}
		return specialData;
	}
	
}
