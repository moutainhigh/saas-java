package com.hq.storeMS.service.orderNotes.bs.update;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.common.constants.OrderClientConstants;
import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.data.ChargeBackItem;
import com.zenmind.common.hotSwap.HotSwap;

public class UntiePrdCardUpdateFilter {
	
	public static UntiePrdCardUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(UntiePrdCardUpdateFilter.class);
	}
	
	public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() != OrderTypeEnum.PURCHASE.ordinal()){
			return operateTips;
		}
		List<ChargeBackItem> chargeBackItems = filterPrdCardItem(inputForm.getChargeBackItems());
		if(CollectionUtils.isEmpty(chargeBackItems)) {
			return operateTips;
		}
		long payTime = order.getPayTime();
		for (ChargeBackItem chargeBackItem : chargeBackItems) {
			int count = chargeBackItem.getCount();
			for (int i = 0; i < count; i++) {
				LeaguerDetailBeanHelper.getInstance().untieProductCard(leaguer.getLeaguerProductCardMap(), chargeBackItem.getPgId(), payTime);
			}
		}
		return operateTips;
	}
	
	private List<ChargeBackItem> filterPrdCardItem(List<ChargeBackItem> chargeBackItems){
		List<ChargeBackItem> result = new ArrayList<ChargeBackItem>();
		for (ChargeBackItem chargeBackItem : chargeBackItems) {
			if(isPrdCardItem(chargeBackItem)) {
				result.add(chargeBackItem);
			}
		}
		return result;
	}
	
	private boolean isPrdCardItem(ChargeBackItem item) {
		String itemId = item.getItemId();
		if(itemId.startsWith(OrderClientConstants.ORDER_BUY_ITEM_ID_SUFFFIX) || itemId.startsWith(OrderClientConstants.ORDER_DONATION_ITEM_ID_SUFFFIX)) {
			if(item.getBuyType() == BuyTypeEnum.PRDCARD.ordinal()) {
				return true;
			}
		}
		return false;
	}
}
