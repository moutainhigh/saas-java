package com.hq.storeMS.service.splitData.bs.mgrHelper;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class OutsiderLeaguerMgr {
	
	public static OutsiderLeaguerMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OutsiderLeaguerMgr.class);
	}
	
	public void splitData(long storeId) {
		try {
			StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);
			StoreLeaguerInfoMgr.getInstance().addOutsiderLeaguer(storeData);
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "OutsiderLeaguerMgr[splitData]", "添加散客男女数据失败"+storeId, e);
		}
	}
}
