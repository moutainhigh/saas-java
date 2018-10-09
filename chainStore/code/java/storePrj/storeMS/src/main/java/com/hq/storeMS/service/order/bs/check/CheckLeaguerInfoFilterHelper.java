package com.hq.storeMS.service.order.bs.check;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class CheckLeaguerInfoFilterHelper {

	public static CheckLeaguerInfoFilterHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CheckLeaguerInfoFilterHelper.class);
	}

	private List<ICheckFilter> filterChains = new ArrayList<ICheckFilter>();

	public CheckLeaguerInfoFilterHelper() {
		// 会员卡余额检验
		filterChains.add(new ICheckFilter() {
			@Override
			public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems) {
				return MemberCardBalanceFilter.getInstance().check(order, leaguer, payItems);
			}
		});
		// 客户次卡抵扣次数检验
		filterChains.add(new ICheckFilter() {
			@Override
			public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems) {
				return PrdCardCountFilter.getInstance().check(order, leaguer, payItems);
			}
		});
		// 客户预存卡抵扣次数检验
		filterChains.add(new ICheckFilter() {
			@Override
			public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems) {
				return PreStoreCardCountFilter.getInstance().check(order, leaguer, payItems);
			}
		});
		// 应结金额与实付金额检验
		filterChains.add(new ICheckFilter() {
			@Override
			public OperateTips check(Order order, LeaguerDetail leaguer, List<PayItem> payItems) {
				return PayAmountFilter.getInstance().check(order, leaguer, payItems);
			}
		});
	}

	public OperateTips check(Order order, List<PayItem> payItems) {
		String leaguerId = order.getLeaguerId();
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().get(order.getStoreId(), leaguerId);
		for (ICheckFilter filter : filterChains) {
			OperateTips operateTips = filter.check(order, leaguer, payItems);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
		}
		return OperateTips.newInstance(true);
	}
}
