package com.hq.storeMS.service.orderNotes.bs.update;

import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateStatusApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.bs.OrderNotesMgr;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;
import com.hq.storeMS.service.orderNotes.data.OrderNotesBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderStatusUpdateFilter {
	
	public static OrderStatusUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(OrderStatusUpdateFilter.class);
	}

	public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() != OrderTypeEnum.PURCHASE.ordinal()){
			return operateTips;
		}
		int status = OrderStatusEnum.CHARGEBACK_PART.ordinal();
		OrderNotes notes = OrderNotesMgr.getInstance().get(order.getStoreId(), order.getId());
		Order newOrder = OrderMgr.getInstance().get(order.getStoreId(), order.getId());
		if(OrderNotesBeanHelper.getInstance().checkItemInfo(notes, newOrder)) {
			status=OrderStatusEnum.CHARGEBACK_ALL.ordinal();
		}
		if(newOrder.getStatus() != status) {
			OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
			updateApiForm.setUpdateType(OrderUpdateType.UpdateState.ordinal());
			updateApiForm.setStoreId(newOrder.getStoreId());
			OrderUpdateStatusApiForm updateStatusData = OrderUpdateStatusApiForm.newInstance();
			updateStatusData.setStatus(status);
			updateApiForm.setUpdateStatusData(updateStatusData);
			OrderMgr.getInstance().update(newOrder.getId(), updateApiForm);
		}
		return operateTips;
	}
}
