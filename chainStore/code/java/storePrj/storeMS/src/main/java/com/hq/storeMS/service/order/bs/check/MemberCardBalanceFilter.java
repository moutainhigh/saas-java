package com.hq.storeMS.service.order.bs.check;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class MemberCardBalanceFilter {
	
	public static MemberCardBalanceFilter getInstance() {
		return HotSwap.getInstance().getSingleton(MemberCardBalanceFilter.class);
	}
	
	public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()){//购买消费
			float memshipCardCost=LeaguerDetailBeanHelper.getInstance().getMemshipCardCost(payItems);//会员卡抵扣金额
			if(memshipCardCost == 0){
				return operateTips;
			}
			boolean b = LeaguerDetailBeanHelper.getInstance().isBalanceGteCost(leaguer.getLeaguerMemberCard(), memshipCardCost);
			if(!b) {
				operateTips.setSuccess(false);
				operateTips.setTips("会员卡余额不足");
			}
		}
		return operateTips;
	}
}
