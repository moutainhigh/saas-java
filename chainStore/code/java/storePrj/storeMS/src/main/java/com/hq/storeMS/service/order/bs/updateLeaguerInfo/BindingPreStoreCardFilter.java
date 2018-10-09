package com.hq.storeMS.service.order.bs.updateLeaguerInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.apiData.PreStoreCardAddForm;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.productCardDetail.data.ProductCardItemEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerPrdCardItem;
import com.zenmind.common.hotSwap.HotSwap;

public class BindingPreStoreCardFilter {

	public static BindingPreStoreCardFilter getInstance() {
		return HotSwap.getInstance().getSingleton(BindingPreStoreCardFilter.class);
	}

	public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
		OperateTips operateTips = OperateTips.newInstance(true);
		if (order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()) {// 购买消费
			if (!bindingCard(order.getId(), leaguer, order.getBuyItems())) {
				operateTips.setSuccess(false);
				operateTips.setTips("新增预存卡信息失败");
				return operateTips;
			}
		}
		return operateTips;
	}

	// 绑定新的预存卡
	private boolean bindingCard(long orderId, LeaguerDetail leaguer, List<BuyItem> buyItems) {
		List<LeaguerPrdCardItem> leaguerPrdCardItems = getLeaguerPrdCardItems(buyItems);
		if(CollectionUtils.isEmpty(leaguerPrdCardItems)) {
			return true;
		}
		PreStoreCardAddForm inputForm = PreStoreCardAddForm.newInstance();
		inputForm.setLeaguerId(leaguer.getId());
		inputForm.setOrderId(orderId);
		inputForm.setLeaguerPrdCardItems(leaguerPrdCardItems);
		return LeaguerDetailBeanHelper.getInstance().addPreStoreCard(leaguer, inputForm);
	}
	
	private List<LeaguerPrdCardItem> getLeaguerPrdCardItems(List<BuyItem> buyItems) {
		List<LeaguerPrdCardItem> list = new ArrayList<LeaguerPrdCardItem>();
		for (BuyItem item : buyItems) {
			int itemType = getItemType(item.getBuyType());
			if(item.getRestCount() > 0 && itemType != ServerConstants.NONE_TYPE) {
				LeaguerPrdCardItem p = LeaguerPrdCardItem.newInstance();
				p.setCount(item.getRestCount());
				p.setRestCount(item.getRestCount());
				p.setItemType(itemType);
				p.setPgId(item.getPgId());
				list.add(p);
			}
		}
		return list;
	}
	
	private int getItemType(int buyTeyp) {
		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(buyTeyp);
		if(buyTypeEnum == BuyTypeEnum.GOODS) {
			return ProductCardItemEnum.GOODS.ordinal();
		}
		if(buyTypeEnum == BuyTypeEnum.PACKAGE) {
			return ProductCardItemEnum.PACKAGE.ordinal();
		}
		if(buyTypeEnum == BuyTypeEnum.PRODUCT) {
			return ProductCardItemEnum.PRODUCT.ordinal();
		}
		return ServerConstants.NONE_TYPE;
	}
}
