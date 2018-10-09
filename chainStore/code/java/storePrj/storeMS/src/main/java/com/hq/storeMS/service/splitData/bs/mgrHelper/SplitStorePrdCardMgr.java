package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.Map;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.PrdCardType;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.sysDataInit.data.SysInitTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitStorePrdCardMgr {
	
	public static SplitStorePrdCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitStorePrdCardMgr.class);
	}
	
	public void splitData(long storeId) {
		try {
			StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
			splitMark4StoreCardInfo(storeData);
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitStoreCardMgr[splitData]", "拆分卡包数据失败"+storeId, e);
		}
	}

	//增加默认分类信息
	private void splitMark4StoreCardInfo(StoreCardInfo storeData) {
		if(storeData.getSplitMark() == SplitMarkEnum.FINISH.ordinal()) {
			Map<String, PrdCardType> prdCardTypeMap = storeData.getPrdCardTypeMap();
			PrdCardType type = PrdCardType.newInstance();
			type.setId(SysInitTypeEnum.UnClassify.getId());
			type.setName(SysInitTypeEnum.UnClassify.getName());
			prdCardTypeMap.put(type.getId(), type);
			storeData.setSplitMark(SplitMarkEnum.FINISH_LEVEL2.ordinal());
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		}
	}
}
