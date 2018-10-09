package com.hq.chainStore.service.orderNotes.bs;

import com.hq.chainStore.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.chainStore.service.orderNotes.data.OrderNotes;
import com.hq.chainStore.service.orderNotes.data.OrderNotesDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeBackRecordMgr {

	public static ChargeBackRecordMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeBackRecordMgr.class);
	}
	
	private final String module = "chargeBackRecord";
	
	public OrderNotes addChargeBackRecord(long orderId, ChargeBackRecordAddForm inputForm) {
		return OrderNotesDAO.getInstance().addSubDomains(orderId, inputForm, module);
	}
}
