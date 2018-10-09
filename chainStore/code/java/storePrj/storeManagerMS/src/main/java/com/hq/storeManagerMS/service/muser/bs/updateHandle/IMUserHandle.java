package com.hq.storeManagerMS.service.muser.bs.updateHandle;

import com.hq.storeManagerMS.service.common.OperateTips;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateApiForm;

public interface IMUserHandle {
	public OperateTips update(MUserUpdateApiForm formInfo);
}
