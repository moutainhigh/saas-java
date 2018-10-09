package com.hq.storeMS.service.orderNotes.bs.check;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.zenmind.common.hotSwap.HotSwap;

public class CheckFilterHelper {

	public static CheckFilterHelper getInstance() {
		return HotSwap.getInstance().getSingleton(CheckFilterHelper.class);
	}

	private List<ICheckFilter> filterChains = new ArrayList<ICheckFilter>();

	public CheckFilterHelper() {
		// 订单状态检验
		filterChains.add(new ICheckFilter() {
			@Override
			public OperateTips check(Order order, OrderNotes orderNotes, ChargeBackRecordAddForm inputForm) {
				return CheckOrderStatusFilter.getInstance().check(order, orderNotes, inputForm);
			}
		});
		// 订单购买项个数与金额检验
		filterChains.add(new ICheckFilter() {
			@Override
			public OperateTips check(Order order, OrderNotes orderNotes, ChargeBackRecordAddForm inputForm) {
				return CheckCountAndCostFilter.getInstance().check(order, orderNotes, inputForm);
			}
		});
	}

	public OperateTips check(Order order, OrderNotes orderNotes, ChargeBackRecordAddForm inputForm) {
		for (ICheckFilter filter : filterChains) {
			OperateTips operateTips = filter.check(order, orderNotes, inputForm);
			if (!operateTips.isSuccess()) {
				return operateTips;
			}
		}
		return OperateTips.newInstance(true);
	}
}
