package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItem;
import com.hq.storeMS.service.storeCardInfo.data.ProductCardTypeEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitLeaguerProductCardDetailMgr {

	public static SplitLeaguerProductCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitLeaguerProductCardDetailMgr.class);
	}

	public void splitData(long storeId) {
		try {
			List<LeaguerDetail> leaguerDetails = LeaguerDetailMgr.getInstance().getLeaguerDetailListByStoreId(storeId);
			for (LeaguerDetail detail : leaguerDetails) {
				updateDetail(storeId, detail);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitLeaguerProductCardDetailMgr[splitData]", "拆分客户次卡数据失败"+storeId, e);
		}
	}

	private void updateDetail(long storeId, LeaguerDetail data) {
		try {
			SplitMarkEnum splitMarkEnum = SplitMarkEnum.valueOf(data.getPrdCardSplitMark());
			if(splitMarkEnum == SplitMarkEnum.AWAIT) {
				Collection<LeaguerProductCard> values = data.getLeaguerProductCardMap().values();
				for (LeaguerProductCard leaguerProductCard : values) {
					updatePrdCardItem(storeId, leaguerProductCard);
				}
				data.setPrdCardSplitMark(SplitMarkEnum.FINISH.ordinal());
				LeaguerDetailMgr.getInstance().updateSimple(data);
			}
		} catch (Exception e) {
			final String logId = "SplitLeaguerProductCardDetailMgr[updateDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(data);
			MainLog.error(LogModule.LeaguerDetail, logId, reason, e);
		}
		
	}
	
	private void updatePrdCardItem(long storeId, LeaguerProductCard prdCard) {
		List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();
		
		ProductCardDetail cardDetail = ProductCardDetailMgr.getInstance().get(storeId, prdCard.getCardId());
		ProductCardTypeEnum productCardTypeEnum = ProductCardTypeEnum.valueOf(cardDetail.getType());
		switch (productCardTypeEnum) {
		case LIMIT_PRDANDTIME:
			Map<Long, Long> useCountMap = prdCard.getUseCountMap();
			if(MapUtils.isNotEmpty(useCountMap)) {
				Set<Long> keys = useCountMap.keySet();
				for (Long key : keys) {
					long useTime = useCountMap.get(key);
					useTime = useTime < 0 ? 0 : useTime;
					productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(key+"", (int)useTime));
				}
			}
			break;
		case LIMIT_TIMEBUTPRD:
			productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(ServerConstants.FOREVER_INT_VALUE+"", (int)prdCard.getCount()));
			break;
		case NOLIMIT_PRDANDTIME:
			productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(ServerConstants.FOREVER_INT_VALUE+"", ServerConstants.FOREVER_INT_VALUE));
			break;
		default:
			break;
		}
		
		prdCard.setProductCardItems(productCardItems);
	}
}
