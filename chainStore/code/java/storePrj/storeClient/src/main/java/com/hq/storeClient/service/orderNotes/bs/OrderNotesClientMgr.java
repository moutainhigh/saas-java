package com.hq.storeClient.service.orderNotes.bs;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeClient.service.orderNotes.apiData.RevokeContentAddForm;
import com.hq.storeClient.service.orderNotes.data.OrderNotes;
import com.hq.storeClient.service.orderNotes.data.OrderNotesDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class OrderNotesClientMgr {

	public static OrderNotesClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderNotesClientMgr.class);
	}

	public OrderNotes getOrderNotes(long storeId, long orderId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, orderId);
		return OrderNotesDAO.getInstance().findOne(uriPath);
	}

	public OrderNotes addChargeBackRecord(long storeId, long orderId, ChargeBackRecordAddForm inputForm) {
		String actionName = StringUtils4Client.format("{}/{}/{}", "chargeBackRecord", storeId, orderId);
		RestResp restResp = OrderNotesDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), OrderNotes.class);
	}
	
	public OrderNotes revokeOrder(long storeId, long orderId, RevokeContentAddForm inputForm) {
		String actionName = StringUtils4Client.format("{}/{}/{}", "revokeContent", storeId, orderId);
		RestResp restResp = OrderNotesDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), OrderNotes.class);
	}

}
