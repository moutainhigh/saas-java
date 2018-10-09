package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;

public interface ILeaguerHandler {
	public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm);
}
