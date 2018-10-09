package com.hq.storeMS.service.orderNotes.bs.update;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PayTypeEnum;
import com.hq.storeMS.service.arrearage.apiData.ArrearageAddPaymentHistoryApiForm;
import com.hq.storeMS.service.arrearage.bs.ArrearageMgr;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.arrearage.data.PaymentTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageUpdateFilter {
	
	public static ArrearageUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageUpdateFilter.class);
	}

	public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() != OrderTypeEnum.PURCHASE.ordinal()){
			return operateTips;
		}
		List<PayItem> payItems = inputForm.getPayItems();
		float arrearageCost = getArrearageCost(payItems);
		if(arrearageCost == 0f) {
			return operateTips;
		}
		
		Arrearage arrearage = ArrearageMgr.getInstance().findArrearageByOrderId(order.getStoreId(), order.getId());
		ArrearageAddPaymentHistoryApiForm addForm = ArrearageAddPaymentHistoryApiForm.newInstance();
		PayItem item = PayItem.newInstance();
		item.setCost(arrearageCost);
		item.setPayType(PayTypeEnum.CASH.ordinal());
		addForm.addPayItem(item);
		addForm.setPaymentType(PaymentTypeEnum.ChargeBack.ordinal());
		operateTips = ArrearageMgr.getInstance().addPaymentHistory(order.getStoreId(), arrearage.getId(), addForm);
		return operateTips;
	}
	
	private float getArrearageCost(List<PayItem> payItems) {
		float cost = 0;
		for (PayItem payItem : payItems) {
			if (payItem.getPayType() == PayTypeEnum.ARREARAGE.ordinal()) {
				cost = BigDecimalUtil.round(payItem.getCost(), 2);
				break;
			}
		}
		return cost;
	}
	
}
