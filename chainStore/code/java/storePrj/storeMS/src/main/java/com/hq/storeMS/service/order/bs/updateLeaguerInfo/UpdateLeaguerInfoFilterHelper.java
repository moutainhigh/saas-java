package com.hq.storeMS.service.order.bs.updateLeaguerInfo;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class UpdateLeaguerInfoFilterHelper {

	public static UpdateLeaguerInfoFilterHelper getInstance() {
		return HotSwap.getInstance().getSingleton(UpdateLeaguerInfoFilterHelper.class);
	}

	private List<IUpdateFilter> filterChains = new ArrayList<IUpdateFilter>();

	public UpdateLeaguerInfoFilterHelper() {
		// 减少会员卡余额
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return MemberCardBalanceUpdateFilter.getInstance().reduceBalance(order, leaguer);
			}
		});
		// 绑定客户次卡
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return BindingPrdCardFilter.getInstance().updateInfo(order, leaguer);
			}
		});
		// 减少客户次卡的次数
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return ReducePrdCardCountFilter.getInstance().updateInfo(order, leaguer);
			}
		});
		//修改客户消费信息
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return IncreaseLeaguerOrderInfoFilter.getInstance().updateInfo(order, leaguer);
			}
		});
		//会员卡充值更新余额信息
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return MemberCardBalanceUpdateFilter.getInstance().rechargeMemberCard(order, leaguer);
			}
		});
		
		// 增加客户预存卡信息
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return BindingPreStoreCardFilter.getInstance().updateInfo(order, leaguer);
			}
		});
		// 减少客户预存卡的次数
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer) {
				return ReducePreStoreCardCountFilter.getInstance().updateInfo(order, leaguer);
			}
		});
	}

	public OperateTips updateInfo(Order order) {
		String leaguerId = order.getLeaguerId();
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().get(order.getStoreId(), leaguerId);
		for (IUpdateFilter filter : filterChains) {
			OperateTips operateTips = filter.updateInfo(order, leaguer);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
		}
		LeaguerDetailMgr.getInstance().updateLeaguerCard(leaguer);
		return OperateTips.newInstance(true);
	}
}
