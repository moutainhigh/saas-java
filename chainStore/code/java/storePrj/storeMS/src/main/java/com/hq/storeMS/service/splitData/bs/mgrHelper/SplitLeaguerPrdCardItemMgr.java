package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItem;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerPrdCardItem;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitLeaguerPrdCardItemMgr {

	public static SplitLeaguerPrdCardItemMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitLeaguerPrdCardItemMgr.class);
	}

	public void splitData(long storeId) {
		try {
			List<LeaguerDetail> leaguerDetails = LeaguerDetailMgr.getInstance().getLeaguerDetailListByStoreId(storeId);
			for (LeaguerDetail detail : leaguerDetails) {
				updateDetail(detail);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitLeaguerProductCardDetailMgr[splitData]", "拆分客户次卡数据失败"+storeId, e);
		}
	}

	private void updateDetail(LeaguerDetail data) {
		try {
			SplitMarkEnum splitMarkEnum = SplitMarkEnum.valueOf(data.getPrdCardSplitMark());
			if(splitMarkEnum == SplitMarkEnum.FINISH) {
				Collection<LeaguerProductCard> values = data.getLeaguerProductCardMap().values();
				for (LeaguerProductCard leaguerProductCard : values) {
					updateLeagerPrdItem(leaguerProductCard);
				}
				data.setPrdCardSplitMark(SplitMarkEnum.FINISH_LEVEL2.ordinal());
				LeaguerDetailMgr.getInstance().updateSimple(data);
			}
		} catch (Exception e) {
			final String logId = "SplitLeaguerPrdCardItemMgr[updateDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(data);
			MainLog.error(LogModule.LeaguerDetail, logId, reason, e);
		}
	}

	private void updateLeagerPrdItem(LeaguerProductCard leaguerProductCard) {
		List<LeaguerPrdCardItem> leaguerPrdCardItems = leaguerProductCard.getLeaguerPrdCardItems();
		if(CollectionUtils.isNotEmpty(leaguerPrdCardItems)) {
			return ;
		}
		List<ProductCardItem> productCardItems = leaguerProductCard.getProductCardItems();
		for (ProductCardItem productCardItem : productCardItems) {
			leaguerPrdCardItems.add(LeaguerPrdCardItem.newInstance(productCardItem));
		}
	}
}
