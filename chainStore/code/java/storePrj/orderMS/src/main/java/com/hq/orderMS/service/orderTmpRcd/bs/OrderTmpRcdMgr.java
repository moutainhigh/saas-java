package com.hq.orderMS.service.orderTmpRcd.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.orderMS.common.constants.ServerConstants;
import com.hq.orderMS.service.order.data.OrderOriginEnum;
import com.hq.orderMS.service.orderTmpRcd.apiData.OrderTmpRcdQueryForm;
import com.hq.orderMS.service.orderTmpRcd.data.OrderTmpRcd;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderTmpRcdMgr {
	public static OrderTmpRcdMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderTmpRcdMgr.class);
	}

	public void addWithId(OrderTmpRcd target) {
		OrderTmpRcdDataHolder.getInstance().addWithId(target);
	}
	
	public void delete(String id) {
		OrderTmpRcdDataHolder.getInstance().delete(id);
	}
	
	public void delete(long storeId, long orderId) {
		delete(OrderTmpRcd.generateId(storeId, orderId));
	}

	public List<OrderTmpRcd> findList(OrderTmpRcdQueryForm queryForm) {
		return OrderTmpRcdDataHolder.getInstance().findList(queryForm);
	}
	
	public void checkOrderTmpRcd() {
		OrderTmpRcdQueryForm queryForm = OrderTmpRcdQueryForm.newInstance();
		long twoHourBefore = System.currentTimeMillis() - 2 * ServerConstants.ONE_HOUR;
		queryForm.setMinTime(twoHourBefore).setOrigin(OrderOriginEnum.CUSTOMER.ordinal());
		List<OrderTmpRcd> list = findList(queryForm);
		if(CollectionUtils.isNotEmpty(list)) {
			for (OrderTmpRcd orderTmpRcd : list) {
				delete(orderTmpRcd.getId());
			}
		}
	}
}
