package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.DataOriginEnum;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItem;
import com.hq.storeMS.service.storeCardInfo.data.PrdInCard;
import com.hq.storeMS.service.storeCardInfo.data.PrdInCardEnum;
import com.hq.storeMS.service.storeCardInfo.data.ProductCardTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitProductCardDetailMgr {

	public static SplitProductCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitProductCardDetailMgr.class);
	}

	public void splitData(long storeId) {
		try {
			List<ProductCardDetail> list = ProductCardDetailMgr.getInstance().getListByStoreId(storeId);
			for (ProductCardDetail productCardDetail : list) {
				updateProductCardDetail(productCardDetail);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitProductCardDetailMgr[splitData]", "拆分次卡详情数据失败"+storeId, e);
		}
	}

	private void updateProductCardDetail(ProductCardDetail data) {
		try {
			SplitMarkEnum splitMarkEnum = SplitMarkEnum.valueOf(data.getSplitMark());
			if(splitMarkEnum == SplitMarkEnum.AWAIT && data.getOrigin() == DataOriginEnum.ZMT.ordinal()) {
				List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();
				ProductCardTypeEnum productCardTypeEnum = ProductCardTypeEnum.valueOf(data.getType());
				switch (productCardTypeEnum) {
				case LIMIT_PRDANDTIME:
					Map<Long, PrdInCard> productMap = data.getProductMap();
					if(MapUtils.isNotEmpty(productMap)) {
						Collection<PrdInCard> values = productMap.values();
						for (PrdInCard prdInCard : values) {
							if(prdInCard.getType() == PrdInCardEnum.LIMITTIME.ordinal()) {
								productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(prdInCard.getPrdId()+"", prdInCard.getCount()));
							}else {
								productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(prdInCard.getPrdId()+"", ServerConstants.FOREVER_INT_VALUE));
							}
						}
					}
					break;
				case LIMIT_TIMEBUTPRD:
					productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(ServerConstants.FOREVER_INT_VALUE+"", data.getTime()));
					break;
				case NOLIMIT_PRDANDTIME:
					productCardItems.add(ProductCardItem.newInstanceByPrdAndTime(ServerConstants.FOREVER_INT_VALUE+"", ServerConstants.FOREVER_INT_VALUE));
					break;
				default:
					break;
				}
				
				data.setSplitMark(SplitMarkEnum.FINISH.ordinal());
				data.setProductCardItems(productCardItems);
				ProductCardDetailMgr.getInstance().update(data);
			}
		}  catch (Exception e) {
			final String logId = "SplitProductCardDetailMgr[updateProductCardDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(data);
			MainLog.error(LogModule.ProductCardDetail, logId, reason, e);
		}
	}
}
