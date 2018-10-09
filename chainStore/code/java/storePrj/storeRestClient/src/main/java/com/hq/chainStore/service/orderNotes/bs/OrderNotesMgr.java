package com.hq.chainStore.service.orderNotes.bs;

import com.hq.chainStore.service.orderNotes.data.OrderNotes;
import com.hq.chainStore.service.orderNotes.data.OrderNotesDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderNotesMgr {

	public static OrderNotesMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderNotesMgr.class);
	}
	
	@Deprecated
	public OrderNotes getOrderNotes(long orderId) {
		return OrderNotesDAO.getInstance().get(orderId);
	}
	
	public OrderNotes getOrderNotes(long storeId, long orderId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderNotesDAO.getInstance().findOne(uriPath);
	}
	
}
