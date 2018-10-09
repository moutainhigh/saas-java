package com.hq.chainMS.service.chain.bs.updateHandle;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chain.apiData.ChainUpdateForm;

public interface IChainHandle {
	public OperateTips update(ChainUpdateForm formInfo);
}
