package com.hq.storeMS.service.order.bs.check;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class PayAmountFilter {
	
	public static PayAmountFilter getInstance() {
		return HotSwap.getInstance().getSingleton(PayAmountFilter.class);
	}
	
	public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems){
		return OperateTips.newInstance(true);
	}
}
