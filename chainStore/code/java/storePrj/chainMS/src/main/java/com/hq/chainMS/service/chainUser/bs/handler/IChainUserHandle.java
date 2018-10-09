package com.hq.chainMS.service.chainUser.bs.handler;

import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateForm;
import com.hq.chainMS.service.common.OperateTips;

public interface IChainUserHandle {
	public OperateTips update(ChainUserUpdateForm updateForm);
}
