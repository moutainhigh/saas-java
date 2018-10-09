package com.hq.storeMS.service.order.bs.updateLeaguerInfo;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class IncreaseLeaguerOrderInfoFilter {
	
	public static IncreaseLeaguerOrderInfoFilter getInstance() {
		return HotSwap.getInstance().getSingleton(IncreaseLeaguerOrderInfoFilter.class);
	}
	
	public OperateTips updateInfo(Order order, LeaguerDetail leaguer){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(!increaseLeaguerOrderInfo(leaguer, order)) {
			operateTips.setSuccess(false);
			operateTips.setTips("修改客户消费信息失败");
			return operateTips;
		}
		return operateTips;
	}
	
	//修改客户订单数 客单价信息
	private boolean increaseLeaguerOrderInfo(LeaguerDetail leaguer, Order order){
		try {
			//设置 客户的消费次数、消费总额、消费时间等信息
			int consumeCount = leaguer.getConsumeCount() + 1;
			float consumeAmount = BigDecimalUtil.round(leaguer.getConsumeAmount() + order.getCost(), 2);
			long lastConsumeTime = System.currentTimeMillis();
			long firstConsumeTime = leaguer.getFirstConsumeTime() == 0 ? lastConsumeTime : leaguer.getFirstConsumeTime();
			
			leaguer.setConsumeCount(consumeCount);
			leaguer.setConsumeAmount(consumeAmount);
			leaguer.setLastConsumeTime(lastConsumeTime);
			leaguer.setFirstConsumeTime(firstConsumeTime);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
