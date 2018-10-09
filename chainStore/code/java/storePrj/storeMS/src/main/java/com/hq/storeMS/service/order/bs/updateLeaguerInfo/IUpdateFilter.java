package com.hq.storeMS.service.order.bs.updateLeaguerInfo;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;

public interface IUpdateFilter {
	public OperateTips updateInfo(Order order, LeaguerDetail leaguer);
}
