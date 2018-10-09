package com.hq.storeMS.service.orderNotes.bs.update;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;

public interface IUpdateFilter {
	public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm);
}
