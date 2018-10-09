package com.hq.chainMS.service.chainCard.bs.updateHandle;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateApiForm;

public interface ICardInfoHandle {
	public OperateTips update(long chainId, ChainCardUpdateApiForm updateForm);
}
