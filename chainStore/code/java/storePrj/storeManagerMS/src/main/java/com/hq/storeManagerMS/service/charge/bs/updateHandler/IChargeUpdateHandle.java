package com.hq.storeManagerMS.service.charge.bs.updateHandler;

import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerMS.service.common.OperateTips;

public interface IChargeUpdateHandle {
	public OperateTips update(ChargeUpdateApiForm updateForm);
}
