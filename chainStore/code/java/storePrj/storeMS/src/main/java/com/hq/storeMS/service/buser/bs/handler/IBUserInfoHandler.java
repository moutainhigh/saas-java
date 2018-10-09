package com.hq.storeMS.service.buser.bs.handler;

import com.hq.storeMS.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeMS.service.common.OperateTips;

public interface IBUserInfoHandler {
	public OperateTips update(BUserUpdateApiForm formInfo);
}
