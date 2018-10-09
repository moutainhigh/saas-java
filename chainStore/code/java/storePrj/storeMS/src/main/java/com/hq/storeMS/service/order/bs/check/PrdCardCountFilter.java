package com.hq.storeMS.service.order.bs.check;

import java.util.List;

import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.ReduceProductCardCountForm;
import com.zenmind.common.hotSwap.HotSwap;

public class PrdCardCountFilter {
	
	public static PrdCardCountFilter getInstance() {
		return HotSwap.getInstance().getSingleton(PrdCardCountFilter.class);
	}
	
	public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()){//购买消费
			if(!reduceProductCardCount(leaguer, order.getDelimitCardItems())){
				operateTips.setSuccess(false);
				operateTips.setTips("客户次卡次数不够抵消");
				return operateTips;
			}
		}
		return operateTips;
	}
	
	private boolean reduceProductCardCount(LeaguerDetail leaguer, List<DelimitCardItem> delimitCardItems){
		String leaguerId = leaguer.getId();
		for (DelimitCardItem item : delimitCardItems) {
			ReduceProductCardCountForm dataForm = ReduceProductCardCountForm.newInstance();
			dataForm.setLeaguerProductCardId(item.getLeaguerPrdCardId());
			dataForm.setLeaguerId(leaguerId);
			dataForm.setItemType(item.getItemType());
			dataForm.setPgId(item.getPgId());
			dataForm.setCount(item.getCount());
			if(!LeaguerDetailBeanHelper.getInstance().reduceProductCardCount(leaguer.getLeaguerProductCardMap(), dataForm)){
				return false;
			}
		}
		return true;
	}
	
}
