package com.hq.storeMS.service.orderNotes.bs.update;

import java.util.List;

import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateChargeBackForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderChargeBackCostUpdateFilter {
	
	public static OrderChargeBackCostUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(OrderChargeBackCostUpdateFilter.class);
	}

	public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() != OrderTypeEnum.PURCHASE.ordinal()){
			return operateTips;
		}
		List<PayItem> payItems = inputForm.getPayItems();
		float chargeBackCost = getChargeBackCost(payItems);
		if(chargeBackCost == 0f) {
			return operateTips;
		}
		OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
		updateApiForm.setUpdateType(OrderUpdateType.UpdateChargeBackCost.ordinal());
		updateApiForm.setStoreId(order.getStoreId());
		OrderUpdateChargeBackForm orderUpdateChargeBackForm = OrderUpdateChargeBackForm.newInstance();
		orderUpdateChargeBackForm.setChargeBackCost(chargeBackCost);
		updateApiForm.setOrderUpdateChargeBackForm(orderUpdateChargeBackForm);
		OrderMgr.getInstance().update(order.getId(), updateApiForm);
		return operateTips;
	}
	
	private float getChargeBackCost(List<PayItem> payItems) {
		float cost = 0;
		for (PayItem payItem : payItems) {
			cost += payItem.getCost();
		}
		return BigDecimalUtil.round(cost, 2);
	}
	
}
