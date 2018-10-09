package com.hq.storeMS.service.order.bs.check;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PreStoreCardItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.apiData.PreStoreCardReduceCountForm;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class PreStoreCardCountFilter {
	
	public static PreStoreCardCountFilter getInstance() {
		return HotSwap.getInstance().getSingleton(PreStoreCardCountFilter.class);
	}
	
	public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()){//购买消费
			if(!reduceCount(leaguer, order.getPreStoreCardItems())){
				operateTips.setSuccess(false);
				operateTips.setTips("客户预存卡次数不够抵消");
				return operateTips;
			}
		}
		return operateTips;
	}
	
	private boolean reduceCount(LeaguerDetail leaguer, List<PreStoreCardItem> items){
		for (PreStoreCardItem item : items) {
			PreStoreCardReduceCountForm inputForm = PreStoreCardReduceCountForm.newInstance();
			inputForm.setCount(item.getCount());
			inputForm.setId(item.getPreStoreCardId());
			inputForm.setItemType(item.getItemType());
			inputForm.setPgId(item.getPgId());
			if(!LeaguerDetailBeanHelper.getInstance().reducePreStoreCardCount(leaguer, inputForm)){
				return false;
			}
		}
		return true;
	}
	
}
