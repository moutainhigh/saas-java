package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitStoreLeaguerMgr {
	
	public static SplitStoreLeaguerMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitStoreLeaguerMgr.class);
	}
	
	public void splitData(long storeId) {
		try {
			StoreLeaguerInfo storeData = StoreLeaguerInfoMgr.getInstance().get(storeId);
			splitMark4StoreLeaguerInfo(storeData);
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitStoreLeaguerMgr[splitData]", "拆分客户数据失败"+storeId, e);
		}
	}
	
	//数据拆分
	private void splitMark4StoreLeaguerInfo(StoreLeaguerInfo storeLeaguerInfo) {
		long storeId = storeLeaguerInfo.getStoreId();
		if(storeLeaguerInfo.getSplitMark() == SplitMarkEnum.AWAIT.ordinal()) {
			removeLeaguerDetailData(storeId);
			Collection<Leaguer> values = storeLeaguerInfo.getLeaguerInfoMap().values();
			for (Leaguer leaguer : values) {
				LeaguerDetailMgr.getInstance().addWithId(LeaguerDetail.newInstanceByLeaguer(leaguer, storeId));
			}
			storeLeaguerInfo.setSplitMark(SplitMarkEnum.FINISH.ordinal());
			StoreLeaguerInfoMgr.getInstance().updStoreLeaguerInfo(storeLeaguerInfo);
		}
	}
	
	private void removeLeaguerDetailData(long storeId) {
		LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<LeaguerDetail> page = LeaguerDetailMgr.getInstance().getLeaguerDetailPageInfo(queryForm);
		List<LeaguerDetail> list = page.getList();
		for (LeaguerDetail data : list) {
			LeaguerDetailMgr.getInstance().delete((LeaguerDetail)data);
		}
	}
}
