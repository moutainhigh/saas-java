package com.hq.storeMS.service.orderNotes.bs.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderRestClient.common.constants.OrderClientConstants;
import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.data.ChargeBackItem;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.AddProductCardCountForm;
import com.zenmind.common.hotSwap.HotSwap;

public class PrdCardCountUpdateFilter {
	
	public static PrdCardCountUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(PrdCardCountUpdateFilter.class);
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
		Map<String, DelimitCardItem> map = getDelimitCardItemMap(chargeBackItems, order);
		for (ChargeBackItem chargeBackItem : chargeBackItems) {
			DelimitCardItem delimitCardItem = map.get(chargeBackItem.getItemId());
			AddProductCardCountForm addProductCardCountForm = AddProductCardCountForm.newInstance();
			addProductCardCountForm.setCount(chargeBackItem.getCount());
			addProductCardCountForm.setPgId(delimitCardItem.getPgId());
			addProductCardCountForm.setItemType(delimitCardItem.getItemType());
			addProductCardCountForm.setLeaguerProductCardId(delimitCardItem.getLeaguerPrdCardId());
			LeaguerDetailBeanHelper.getInstance().addProductCardCount(leaguer.getLeaguerProductCardMap(), addProductCardCountForm);
		}
		return operateTips;
	}
	
	private Map<String, DelimitCardItem> getDelimitCardItemMap(List<ChargeBackItem> chargeBackItems, Order order){
		Map<String, DelimitCardItem> result = new HashMap<String, DelimitCardItem>();
		for (ChargeBackItem chargeBackItem : chargeBackItems) {
			result.put(chargeBackItem.getItemId(), order.takeDelimitCardItem(chargeBackItem.getItemId()));
		}
		return result;
	}
	
	private List<ChargeBackItem> filterPrdCardItem(List<ChargeBackItem> chargeBackItems){
		List<ChargeBackItem> result = new ArrayList<ChargeBackItem>();
		for (ChargeBackItem chargeBackItem : chargeBackItems) {
			if(isDelimitCardItem(chargeBackItem)) {
				result.add(chargeBackItem);
			}
		}
		return result;
	}
	
	private boolean isDelimitCardItem(ChargeBackItem item) {
		String itemId = item.getItemId();
		if(itemId.startsWith(OrderClientConstants.ORDER_DELIMITCARD_ITEM_ID_SUFFFIX)) {
			return true;
		}
		return false;
	}
}
