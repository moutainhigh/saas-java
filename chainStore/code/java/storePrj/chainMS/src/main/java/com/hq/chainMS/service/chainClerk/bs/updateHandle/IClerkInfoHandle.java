package com.hq.chainMS.service.chainClerk.bs.updateHandle;

import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateForm;
import com.hq.chainMS.service.common.OperateTips;

public interface IClerkInfoHandle {
	public OperateTips update(long chainId, ChainClerkUpdateForm inputForm);
}
