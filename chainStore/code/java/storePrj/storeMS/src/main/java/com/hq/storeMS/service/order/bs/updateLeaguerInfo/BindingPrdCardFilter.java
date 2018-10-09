package com.hq.storeMS.service.order.bs.updateLeaguerInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.storeCardInfo.data.SellFlagEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class BindingPrdCardFilter {

	public static BindingPrdCardFilter getInstance() {
		return HotSwap.getInstance().getSingleton(BindingPrdCardFilter.class);
	}

	public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
		OperateTips operateTips = OperateTips.newInstance(true);
		if (order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()) {// 购买消费
			if (!bindingProductCard(leaguer, order.getBuyItems())) {
				operateTips.setSuccess(false);
				operateTips.setTips("绑定新购的次卡失败");
				return operateTips;
			}
			if (!bindingDonateProductCard(leaguer, order.getDonationItems())) {
				operateTips.setSuccess(false);
				operateTips.setTips("绑定赠送的次卡失败");
				return operateTips;
			}
		}
		return operateTips;
	}

	// 绑定新购的次卡
	private boolean bindingProductCard(LeaguerDetail leaguer, List<BuyItem> buyItems) {
		List<String> newPrdCardIds = new ArrayList<String>();
		for (BuyItem item : buyItems) {
			if (item.getBuyType() == BuyTypeEnum.PRDCARD.ordinal()) {
				for (int i = 0; i < item.getCount(); i++) {
					newPrdCardIds.add(item.getPgId());
				}
			}
		}
		return binding(leaguer, newPrdCardIds);
	}

	// 绑定赠送的次卡
	private boolean bindingDonateProductCard(LeaguerDetail leaguer, List<DonationItem> donationItems) {
		List<String> newPrdCardIds = new ArrayList<String>();
		for (DonationItem item : donationItems) {
			if (item.getBuyType() == BuyTypeEnum.PRDCARD.ordinal()) {
				for (int i = 0; i < item.getCount(); i++) {
					newPrdCardIds.add(item.getPgId());
				}
			}
		}
		return binding(leaguer, newPrdCardIds);
	}

	private boolean binding(LeaguerDetail leaguer, List<String> newPrdCardIds) {
		if (CollectionUtils.isEmpty(newPrdCardIds)) {// 没有赠送的次卡
			return true;
		}
		
		long storeId = leaguer.getStoreId();
		for (String pcId : newPrdCardIds) {
			ProductCardDetail productCard = ProductCardDetailMgr.getInstance().get(storeId, pcId);
			if (productCard != null) {
				LeaguerDetailBeanHelper.getInstance().purchaseProductCard(leaguer, productCard);
				// 绑定次卡 则认为已销售过改次卡
				productCard.setSellFlag(SellFlagEnum.HAS_SELL.ordinal());
				ProductCardDetailMgr.getInstance().update(productCard);
			}
		}
		return true;
	}
}
