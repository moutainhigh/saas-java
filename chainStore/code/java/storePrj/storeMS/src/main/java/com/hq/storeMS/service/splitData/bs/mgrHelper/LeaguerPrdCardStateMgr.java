package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerPrdCardStateMgr {

	public static LeaguerPrdCardStateMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerPrdCardStateMgr.class);
	}

	public void splitData(long storeId) {
		try {
			List<LeaguerDetail> leaguerDetails = LeaguerDetailMgr.getInstance().getLeaguerDetailListByStoreId(storeId);
			for (LeaguerDetail detail : leaguerDetails) {
				updateDetail(detail);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "LeaguerPrdCardStateMgr[splitData]", "更新客户次卡状态失败"+storeId, e);
		}
	}

	private void updateDetail(LeaguerDetail data) {
		try {
			SplitMarkEnum splitMarkEnum = SplitMarkEnum.valueOf(data.getPrdCardSplitMark());
			if(splitMarkEnum == SplitMarkEnum.FINISH_LEVEL2) {
				Collection<LeaguerProductCard> values = data.getLeaguerProductCardMap().values();
				for (LeaguerProductCard leaguerProductCard : values) {
					LeaguerDetailBeanHelper.getInstance().updateLeaguerProductCardState(leaguerProductCard);
				}
				data.setPrdCardSplitMark(SplitMarkEnum.FINISH_LEVEL3.ordinal());
				LeaguerDetailMgr.getInstance().updateSimple(data);
			}
		} catch (Exception e) {
			final String logId = "SplitLeaguerPrdCardItemMgr[updateDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(data);
			MainLog.error(LogModule.LeaguerDetail, logId, reason, e);
		}
	}
}
