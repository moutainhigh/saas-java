package com.hq.storeMS.service.orderNotes.bs.update;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * <pre>
 * 退单更新数据逻辑 
 * 1. 增加会员卡的余额信息[退款包含会员卡项] 
 * 2. 增加欠款退单记录[退款包含欠款项] 
 * 3. 解绑次卡[购买与赠送的次卡项] -- 相同的次卡 如何区分出是当前订单购买的？ 暂定的算法是：创建时间与订单付款时间比较 差值最小的就是 
 * 4. 更新次卡的次数[划卡项]
 * 5. 更新订单的退款金额
 * 6. 更新订单的状态
 * </pre>
 * 
 * @author kevin
 *
 */
public class UpdateFilterHelper {

	public static UpdateFilterHelper getInstance() {
		return HotSwap.getInstance().getSingleton(UpdateFilterHelper.class);
	}

	private List<IUpdateFilter> filterChains = new ArrayList<IUpdateFilter>();

	public UpdateFilterHelper() {
		// 欠款退单
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm) {
				return ArrearageUpdateFilter.getInstance().updateInfo(order, leaguer, inputForm);
			}
		});
		// 更新会员卡余额
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm) {
				return MemberCardBalanceUpdateFilter.getInstance().updateInfo(order, leaguer, inputForm);
			}
		});
		// 解绑客户次卡
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm) {
				return UntiePrdCardUpdateFilter.getInstance().updateInfo(order, leaguer, inputForm);
			}
		});
		// 更新客户次卡次数
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm) {
				return PrdCardCountUpdateFilter.getInstance().updateInfo(order, leaguer, inputForm);
			}
		});
		// 更新订单的退单金额
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm) {
				return OrderChargeBackCostUpdateFilter.getInstance().updateInfo(order, leaguer, inputForm);
			}
		});
		// 更新订单的状态
		filterChains.add(new IUpdateFilter() {
			@Override
			public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm) {
				return OrderStatusUpdateFilter.getInstance().updateInfo(order, leaguer, inputForm);
			}
		});
	}

	public OperateTips updateInfo(Order order, ChargeBackRecordAddForm inputForm) {
		String leaguerId = order.getLeaguerId();
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().get(order.getStoreId(), leaguerId);
		for (IUpdateFilter filter : filterChains) {
			OperateTips operateTips = filter.updateInfo(order, leaguer, inputForm);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
		}
		LeaguerDetailMgr.getInstance().updateLeaguerCard(leaguer);
		return OperateTips.newInstance(true);
	}
}
