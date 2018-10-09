package com.hq.storeMS.service.orderNotes.bs.check;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;

public interface ICheckFilter {
	public OperateTips check(Order order, OrderNotes orderNotes, ChargeBackRecordAddForm inputForm);
}
