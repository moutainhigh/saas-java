package com.hq.storeMS.service.orderNotes.data;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.apiData.RevokeContentAddForm;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderNotesBeanHelper {
	public static OrderNotesBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(OrderNotesBeanHelper.class);
	}
	
	public boolean addRevokeContent(OrderNotes orderNotes, RevokeContentAddForm data) {
		if(orderNotes == null){
			return false;
		}
		RevokeContent revokeContent = data.toRevokeContent();
		orderNotes.setRevokeContent(revokeContent);
		return true;
	}
	
	public boolean addChargeBackRecord(OrderNotes orderNotes, ChargeBackRecordAddForm data, String orderNumber) {
		if(orderNotes == null){
			return false;
		}
		ChargeBackRecord chargeBackRecord = data.toChargeBackRecord(orderNotes.getChargeBackRecordIndex() + 1);
		Map<Integer, ChargeBackRecord> dataMap = orderNotes.getChargeBackRecordMap();
		boolean success = false;
		if (!dataMap.containsKey(chargeBackRecord.getId()) && orderNotes.getChargeBackRecordIndex() + 1 == chargeBackRecord.getId()) {
			chargeBackRecord.setNumber(genNumber(orderNumber, chargeBackRecord.getId()));
			dataMap.put(chargeBackRecord.getId(), chargeBackRecord);
			orderNotes.setChargeBackRecordIndex(chargeBackRecord.getId());
			success = true;
		}
		return success;
	}
	
	private String genNumber(String orderNumber, long chargeBackRecordId) {
		return orderNumber+StringUtils.leftPad(String.valueOf(chargeBackRecordId), 2, "0");
	}

	//订单与退单的数量对比  订单项的数量比退单项的数量大则为部分退单  否则表示所有项都已退单  订单状态应该为退单状态  
	public boolean checkItemInfo(OrderNotes orderNotes, Order order) {
		Map<String, ItemCountAndCost> countAndCostMap = orderNotes.takeItemCountAndCostMap();
		List<BuyItem> buyItems = order.getBuyItems();
		for (BuyItem item : buyItems) {
			ItemCountAndCost itemCountAndCost = countAndCostMap.get(item.getBuyItemId());
			if(itemCountAndCost == null || item.getCount() > itemCountAndCost.getCount()) {//有剩余未退的货
				return false;
			}
		}
		List<DelimitCardItem> delimitCardItems = order.getDelimitCardItems();
		for (DelimitCardItem item : delimitCardItems) {
			ItemCountAndCost itemCountAndCost = countAndCostMap.get(item.getDelimitCardItemId());
			if(itemCountAndCost == null || item.getCount() > itemCountAndCost.getCount()) {//有剩余未退的货
				return false;
			}
		}
		List<DonationItem> donationItems = order.getDonationItems();
		for (DonationItem item : donationItems) {
			ItemCountAndCost itemCountAndCost = countAndCostMap.get(item.getDonationItemId());
			if(itemCountAndCost == null || item.getCount() > itemCountAndCost.getCount()) {//有剩余未退的货
				return false;
			}
		}
		//尚有金额没退
		if(BigDecimalUtil.round(order.getRealPay(), 2) != BigDecimalUtil.round(order.getChargeBackCost(), 2)) {
			return false;
		}
		return true;
	}
}
